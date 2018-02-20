package Model;

/**
 * Created by ton on 2/5/18.
 */

public class QADataTable {
    String section;
    String detail;
    String detailExtra;
    String radio;
    String comment;
    String detaioNo;

    public String getDetaioNo() {
        return detaioNo;
    }

    public void setDetaioNo(String detaioNo) {
        this.detaioNo = detaioNo;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String header) {
        this.section = header;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getRadio() {
        return radio;
    }

    public void setRadio(String radio) {
        this.radio = radio;
    }

    public String getDetailExtra() {
        return detailExtra;
    }

    public void setDetailExtra(String detailExtra) {
        this.detailExtra = detailExtra;
    }
}
