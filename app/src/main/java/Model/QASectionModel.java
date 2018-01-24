package Model;

import java.util.List;

/**
 * Created by marisalom on 24/01/2018.
 */

public class QASectionModel {
    String qaSectionType;
    String qaSectionDesc;
    QADetailModel qaDetailModel;
    List<QADetailModel> qaDetailModelList;

    public String getQaSectionType() {
        return qaSectionType;
    }

    public void setQaSectionType(String qaSectionType) {
        this.qaSectionType = qaSectionType;
    }

    public String getQaSectionDesc() {
        return qaSectionDesc;
    }

    public void setQaSectionDesc(String qaSectionDesc) {
        this.qaSectionDesc = qaSectionDesc;
    }

    public QADetailModel getQaDetailModel() {
        return qaDetailModel;
    }

    public void setQaDetailModel(QADetailModel qaDetailModel) {
        this.qaDetailModel = qaDetailModel;
    }

    public List<QADetailModel> getQaDetailModelList() {
        return qaDetailModelList;
    }

    public void setQaDetailModelList(List<QADetailModel> qaDetailModelList) {
        this.qaDetailModelList = qaDetailModelList;
    }
}
