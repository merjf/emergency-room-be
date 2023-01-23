package com.portaleps.model.response;

import com.portaleps.dto.ArchiveDTO;
import com.portaleps.dto.ResponseDto;

import java.util.ArrayList;
import java.util.List;

public class ArchiveResponse {

    private ResponseDto responseDto;
    private List<ArchiveDTO> archives;

    public ArchiveResponse(){
        this.archives = new ArrayList<>();
    }

    public ArchiveResponse(ResponseDto responseDto, List<ArchiveDTO> archives){
        this.responseDto = responseDto;
        this.archives = archives;
    }

    public ResponseDto getResponseDto() {
        return responseDto;
    }

    public void setResponseDto(ResponseDto responseDto) {
        this.responseDto = responseDto;
    }

    public List<ArchiveDTO> getArchives() {
        return archives;
    }

    public void setArchives(List<ArchiveDTO> archives) {
        this.archives = archives;
    }
}
