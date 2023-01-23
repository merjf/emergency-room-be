package com.portaleps.helper;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.NumericNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;
import com.portaleps.exception.CustomException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Filter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class QueryBuilder {

    private static final Logger LOGGER = LogManager.getLogger(QueryBuilder.class);

    public enum Filters{
        // Anagrafica
        cognome("vv.cognome = ?"),
        nome("vv.nome = ?"),
        codiceFiscale("vv.cod_fisc = ?"),
        sesso("vv.sesso = ?"),
        dataNascitaStart("vv.data_nascita >= CAST( ? AS DATE)"),
        dataNascitaEnd("vv.data_nascita <= CAST( ? AS DATE)"),

        // Di Accesso
        dataAccessoStart("vv.data_accesso >= CAST( ? AS DATE)"),
        dataAccessoEnd("vv.data_accesso <= CAST( ? AS DATE)"),
        oraAccessoStart("vv.ora_accesso >= CAST( ? AS TIME)"),
        oraAccessoEnd("vv.ora_accesso <= CAST( ? AS TIME)"),
        dataDimissioneStart("vv.data_dimissione >= CAST( ? AS DATE)"),
        dataDimissioneEnd("vv.data_dimissione <= CAST( ? AS DATE)"),
        oraDimissioneStart("vv.ora_dimissione >= CAST( ? AS TIME)"),
        oraDimissioneEnd("vv.ora_dimissione <= CAST( ? AS TIME)"),
        //dataOraAccesso("pa.data_acc BETWEEN CAST( ? AS DATE) AND CAST( ? AS DATE) AND pa.ora_acc BETWEEN CAST( ? AS TIME ) AND CAST( ? AS TIME )"),
        //dataOraDimissione("padim.data_dim BETWEEN CAST( ? AS DATE) AND CAST( ? AS DATE) AND padim.ora_dim BETWEEN CAST( ? AS TIME ) AND CAST( ? AS TIME )"),
        numeroAccesso("vv.id_accesso = ?"),
        colore("vv.colore = ?"),

        // Generici
        chiaveGenerica(" (vv.cognome LIKE ? OR vv.nome LIKE ? OR " +
                " vv.cod_fisc LIKE ? OR vv.sesso LIKE ? OR " +
                " vv.id_accesso LIKE ? OR vv.colore LIKE ? ) ");

        private String statement;

        Filters(String statement) {
            this.statement = statement;
        }
        public String getStatement(){
            return statement;
        }
    }

    private static final String COUNT_ACCESSI = "SELECT COUNT(*) as tot_accessi FROM ps_accessi;";

    private static final String SELECT_FOR_FILTERS = "SELECT vv.colore AS Colore, vv.id_accesso AS Accesso, " +
            "vv.nome AS Nome, vv.cognome AS Cognome, vv.cod_fisc AS \"Codice Fiscale\", " +
            "vv.problema_accesso AS \"Problema Accesso\", vv.data_accesso AS \"Data Accesso\", " +
            "vv.ora_accesso AS \"Ora Accesso\", vv.data_dimissione AS \"Data Dimissione\", " +
            "vv.ora_dimissione AS \"Ora Dimissione\", GROUP_CONCAT(vv.diagnosi SEPARATOR ' - ') as Diagnosi, " +
            "vv.esito AS \"Esito Trattamento\" ";

    private static final String SELECT_FOR_COUNTS = "SELECT COUNT(*) as tot_accessi ";

    private static final String FROM_FOR_FILTERS =  " FROM view_verbali vv ";

    private static final String QUERY_PAZIENTE = "SELECT paa.*, ts.stato, ta.asl, tr.regione " +
            "FROM ps_accessi_ana paa, tab_stati ts, tab_asl ta, tab_regioni tr " +
            "WHERE paa.id_accesso = ? AND ts.id_stato = paa.id_cittadinanza AND tr.id_regione = paa.res_id_regione " +
            "LIMIT 1;";

    private static final String QUERY_ACCETTAZIONE_TRIAGE = "SELECT pa.*, ptc.descrizione as causa, ti.denominazione as istituto, " +
            "ptesenzione.descrizione as esenzione, pterogante.descrizione as erogante, " +
            "ptm.descrizione as mezzo, ptp.descrizione as problema, ptpd.descrizione as dettProblema " +
            "FROM ps_accessi pa " +
            "LEFT JOIN ps_accessi_ana paa ON pa.id_accesso = paa.id_accesso " +
            "LEFT JOIN ps_tipi_causa ptc ON ptc.id_causa = pa.id_causa " +
            "LEFT JOIN tab_istituti ti ON ti.id_istituto = pa.id_istituto_prov " +
            "LEFT JOIN ps_tipi_erogante pterogante ON pterogante.id_erogante = pa.id_erogante " +
            "LEFT JOIN ps_tipi_esenzione ptesenzione ON ptesenzione.id_esenzione = pa.id_esenzione " +
            "LEFT JOIN ps_tipi_mezzo ptm ON ptm.id_mezzo = pa.id_mezzo " +
            "LEFT JOIN ps_tipi_problema ptp ON ptp.id_problema = pa.id_problema " +
            "LEFT JOIN ps_tipi_problema_dett ptpd ON pa.id_problema_dett = ptpd.id_problema_dett " +
            "WHERE pa.id_accesso = ? " +
            "LIMIT 1;";

    private static final String QUERY_CRONOLOGIA_TRIAGE = "SELECT par.*, ptt.colore, wu.cognome , wu.nome "+
            "FROM  ps_accessi_riv par " +
            "LEFT JOIN ps_accessi_ana paa ON paa.id_accesso = par.id_accesso " +
            "LEFT JOIN ps_tipi_triage ptt on ptt.id_triage = par.id_triage " +
            "LEFT JOIN web_utenti wu on wu.uid = par.ins_uid " +
            "WHERE paa.id_accesso = ? ;";

    private static final String QUERY_CONDIZIONI_CLINICHE = "SELECT pai.*, pinfo.descrizione as info, wu.nome, wu.cognome " +
            "FROM  ps_accessi_info pai " +
            "LEFT JOIN ps_accessi_ana paa ON pai.id_accesso = paa.id_accesso " +
            "LEFT JOIN ps_info pinfo ON pinfo.id_info = pai.id_info " +
            "LEFT JOIN web_utenti wu ON wu.uid = pai.ins_uid " +
            "WHERE paa.id_accesso = ? ;";

    private static final String QUERY_ACCETTAZIONI_MEDICI = "SELECT padett.*, wu.nome , wu.cognome " +
            "FROM ps_accessi_dett padett " +
            "LEFT JOIN ps_accessi pa ON pa.id_accesso = padett.id_accesso " +
            "LEFT JOIN web_utenti wu ON padett.car_uid = wu.uid " +
            "WHERE pa.id_accesso = ? ;";

    private static final String QUERY_ITER_DIAGNOSTICO = "SELECT pae.*, wu.cognome , wu.nome , e.descrizione as esame " +
            "FROM ps_accessi_esa pae " +
            "LEFT JOIN ps_accessi_ana paa ON pae.id_accesso = paa.id_accesso " +
            "LEFT JOIN esami e ON pae.id_esame = e.id_esame " +
            "LEFT JOIN web_utenti wu ON wu.uid = pae.ins_uid " +
            "WHERE paa.id_accesso = ? ;";

    private static final String QUERY_SOMMINISTRAZIONI = "SELECT paf.*, wu1.nome , wu1.cognome, wu2.nome as somm_nome, wu2.cognome as somm_cognome, ma.descrizione as farmaco  " +
            "FROM ps_accessi_farm paf " +
            "LEFT JOIN ps_accessi_ana paa ON paf.id_accesso = paa.id_accesso  " +
            "LEFT JOIN mag_articoli ma ON ma.id_articolo = paf.id_articolo " +
            "LEFT JOIN web_utenti wu1 ON wu1.uid = paf.rich_uid " +
            "LEFT JOIN web_utenti wu2 ON wu2.uid = paf.somm_uid " +
            "WHERE paa.id_accesso = ? ;";

    private static final String QUERY_ESITO_DIMISSIONE = "SELECT pad1.*, pte.descrizione as esito, ptm.descrizione as mezzo, " +
            "ti.denominazione as istituto, ptt.descrizione as motivo, GROUP_CONCAT(ta.descrizione SEPARATOR ' - ') as diagnosi, wr.reparto as reparto  " +
            "FROM ps_accessi_dim pad1 " +
            "LEFT JOIN ps_accessi_ana paa ON pad1.id_accesso = paa.id_accesso " +
            "LEFT JOIN ps_accessi_diagn pad2 ON pad1.id_accesso = pad2.id_accesso " +
            "LEFT JOIN ps_tipi_esito pte ON pte.id_esito = pad1.id_esito " +
            "LEFT JOIN web_reparti wr ON wr.id = pad1.id_reparto " +
            "LEFT JOIN ps_tipi_mezzo ptm ON ptm.id_mezzo = pad1.id_mezzo_dim " +
            "LEFT JOIN tab_istituti ti ON ti.id_istituto = pad1.id_istituto_dim " +
            "LEFT JOIN ps_tipi_trasferimento ptt ON pad1.id_trasferimento = ptt.id_trasferimento " +
            "LEFT JOIN tab_diagnosi ta ON ta.id_diagnosi = pad2.id_diagnosi " +
            "WHERE paa.id_accesso = ? " +
            " GROUP BY paa.id_accesso ;";

    private static final String WHERE = " WHERE ";

    private static final String AND = " AND ";

    private static final String GROUP_BY = " GROUP BY vv.id_accesso ";

    private static final String PAGINATION = " LIMIT ? OFFSET ? ";

    private static final String FINAL_CHAR = ";";

    public static String buildSearchPaziente(){
        LOGGER.info("buildSearchPaziente() - start");
        LOGGER.info("buildSearchPaziente() - end");
        return QUERY_PAZIENTE;
    }

    public static String buildSearchAccettazioneTriage(){
        LOGGER.info("buildSearchAccettazioneTriage() - start");
        LOGGER.info("buildSearchAccettazioneTriage() - end");
        return QUERY_ACCETTAZIONE_TRIAGE;
    }

    public static String buildSearchCronologiaTriage(){
        LOGGER.info("buildSearchCronologiaTriage() - start");
        LOGGER.info("buildSearchCronologiaTriage() - end");
        return QUERY_CRONOLOGIA_TRIAGE;
    }

    public static String buildSearchCondizioniCliniche() {
        LOGGER.info("buildSearchCondizioniCliniche() - start");
        LOGGER.info("buildSearchCondizioniCliniche() - end");
        return QUERY_CONDIZIONI_CLINICHE;
    }

    public static String buildSearchAccettazioniMedici(){
        LOGGER.info("buildSearchAccettazioniMedici() - start");
        LOGGER.info("buildSearchAccettazioniMedici() - end");
        return QUERY_ACCETTAZIONI_MEDICI;
    }

    public static String buildSearchIterDiagnostico(){
        LOGGER.info("buildSearchIterDiagnostico() - start");
        LOGGER.info("buildSearchIterDiagnostico() - end");
        return QUERY_ITER_DIAGNOSTICO;
    }

    public static String buildSearchSomministrazioni(){
        LOGGER.info("buildSearchSomministrazioni() - start");
        LOGGER.info("buildSearchSomministrazioni() - end");
        return QUERY_SOMMINISTRAZIONI;
    }

    public static String buildSearchEsitoDimissione(){
        LOGGER.info("buildSearchEsitoDimissione() - start");
        LOGGER.info("buildSearchEsitoDimissione() - end");
        return QUERY_ESITO_DIMISSIONE;
    }

    public static String buildSearchQuery(ObjectNode filters, ArrayNode pagination){
        LOGGER.info("buildSearchQuery() - start");
        StringBuilder query = new StringBuilder(SELECT_FOR_FILTERS + FROM_FOR_FILTERS);
        query.append(appendWhereCondition(filters));
        query.append(GROUP_BY);
        query.append(appendPagination(pagination));
        query.append(FINAL_CHAR);
        LOGGER.info("buildSearchQuery() - end");
        return query.toString();
    }

    public static String buildCountRows(ObjectNode filters){
        LOGGER.info("buildCountRows() - start");
        StringBuilder query = new StringBuilder(SELECT_FOR_COUNTS + FROM_FOR_FILTERS);
        query.append(appendWhereCondition(filters));
        query.append(FINAL_CHAR);
        LOGGER.info("buildCountRows() - end");
        return query.toString();
    }

    public static void applyFilters(ObjectNode filters, PreparedStatement statement){
        LOGGER.info("applyFilters() - start");
        ArrayList<Object> valueParams = extractValueParams(filters);
        int index = 1;
        Iterator<Object> iterator = valueParams.iterator();
        while (iterator.hasNext()) {
            Object item = iterator.next();
            String value = String.valueOf(item);
            try {
                if(item instanceof String){
                    statement.setString(index++, value);
                } else if(item instanceof Long){
                    statement.setLong(index++, Long.parseLong(value));
                } else{
                    statement.setObject(index++, item);
                }
            } catch (SQLException e) {
                LOGGER.error("applyFilters() - bad statement setting: " + item.toString() + " with value: " + value);
                LOGGER.info("applyFilters() - end");
                e.printStackTrace();
                throw new CustomException("Bad statement setting: " + item + " with value: " + value, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        LOGGER.info("applyFilters() - end");
    }

    // PRIVATE METHODS

    private static String appendWhereCondition(ObjectNode filters){
        LOGGER.info("appendWhereCondition() - start");
        StringBuilder filterQuery = new StringBuilder();
        if(!filters.isEmpty()){
            filterQuery.append(WHERE);
            Iterator<Map.Entry<String, JsonNode>> iterator = filters.fields();
            while (iterator.hasNext()) {
                Map.Entry<String, JsonNode> item = iterator.next();
                Filters filter = Filters.valueOf(item.getKey());
                if(filter != null){
                    filterQuery.append(filter.statement);
                    if(iterator.hasNext()){
                        filterQuery.append(AND);
                    }
                }
            }
        }
        LOGGER.info("appendWhereCondition() - end");
        return filterQuery.toString();
    }

    private static String appendPagination(ArrayNode pagination){
        LOGGER.info("appendPagination() - start");
        StringBuilder builder = new StringBuilder();
        if(pagination != null && !pagination.isEmpty()){
            String result = PAGINATION;
            result = result.replaceFirst("[?;]", String.valueOf(pagination.get(0)));
            int offset = pagination.get(1).asInt() * pagination.get(0).asInt() - 1;
            if(offset < 0)
                offset = 0;
            result = result.replaceFirst("[?;]", String.valueOf(offset));
            builder.append(result);
        }
        LOGGER.info("appendPagination() - end");
        return builder.toString();
    }

    private static String applyValueParams(ObjectNode filters, StringBuilder query){
        LOGGER.info("applyValueParams() - start");
        ArrayList<Object> valueParams = extractValueParams(filters);
        int index = 0;
        Pattern p = Pattern.compile("\\?");
        Matcher m = p.matcher(query);
        StringBuilder builder = new StringBuilder(query.length());
        while (m.find()) {
            m.appendReplacement(query, valueParams.get(index++).toString());
        }
        m.appendTail(builder);
        LOGGER.info("applyValueParams() - end");
        return builder.toString();
    }

    private static ArrayList<Object> extractValueParams(ObjectNode filtersParams){
        LOGGER.info("extractValueParams() - start");
        ArrayList<Object> valueParams = new ArrayList<>();
        Iterator<Map.Entry<String, JsonNode>> iterator = filtersParams.fields();
        while (iterator.hasNext()) {
            Map.Entry<String, JsonNode> item = iterator.next();
            if(item.getKey().equalsIgnoreCase(Filters.chiaveGenerica.name())){
                valueParams.addAll(Collections.nCopies(6, "%" + getValue(item.getValue()) + "%"));
            } else if(item.getValue() instanceof ArrayNode){
                for(JsonNode arrayItem : item.getValue()){
                    valueParams.add(getValue(arrayItem));
                }
            } else {
                valueParams.add(getValue(item.getValue()));
            }
        }
        LOGGER.info("extractValueParams() - end");
        return valueParams;
    }

    private static Object getValue(JsonNode item){
        LOGGER.info("getValue() - start");
        if(item instanceof NumericNode){
            LOGGER.info("getValue() - end");
            return item.asLong();
        } else if(item instanceof TextNode){
            LOGGER.info("getValue() - end");
            return item.asText();
        } else{
            LOGGER.info("getValue() - end");
            return item.toString();
        }
    }
}
