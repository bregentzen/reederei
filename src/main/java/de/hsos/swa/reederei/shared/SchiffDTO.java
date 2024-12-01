package de.hsos.swa.reederei.shared;

public class SchiffDTO {
    public long auftragId;
    public String schiffURL;

    public SchiffDTO() {
    }

    public SchiffDTO(long auftragId, String schiffURL) {
        this.auftragId = auftragId;
        this.schiffURL = schiffURL;
    }

    public long getAuftragId() {
        return auftragId;
    }

    public void setAuftragId(long auftragId) {
        this.auftragId = auftragId;
    }

    public String getSchiffURL() {
        return schiffURL;
    }

    public void setSchiffURL(String schiffURL) {
        this.schiffURL = schiffURL;
    }
}
