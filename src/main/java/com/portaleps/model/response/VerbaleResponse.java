package com.portaleps.model.response;

import com.portaleps.dto.ResponseDto;
import com.portaleps.dto.VerbaleDTO;

import java.util.ArrayList;
import java.util.List;

public class VerbaleResponse {

    private ResponseDto response;
    private List<VerbaleDTO> verbali;
    private PaginationResponse pagination;

    public VerbaleResponse() {
        this.verbali = new ArrayList<>();
    }

    public VerbaleResponse(ResponseDto response, List<VerbaleDTO> verbali) {
        this.response = response;
        this.verbali = verbali;
    }

    public ResponseDto getResponse() {
        return response;
    }

    public void setResponse(ResponseDto response) {
        this.response = response;
    }

    public List<VerbaleDTO> getVerbali() {
        return verbali;
    }

    public void setVerbali(List<VerbaleDTO> verbali) {
        this.verbali = verbali;
    }

    public PaginationResponse getPagination() {
        return pagination;
    }

    public void setPagination(PaginationResponse paginationResponse) {
        this.pagination = paginationResponse;
    }
}
