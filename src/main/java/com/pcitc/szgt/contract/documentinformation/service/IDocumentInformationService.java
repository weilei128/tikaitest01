package com.pcitc.szgt.contract.documentinformation.service;

import com.pcitc.szgt.contract.documentinformation.model.Callbackdocument;
import com.pcitc.szgt.contract.documentinformation.model.Callbackdocumentdetail;
import com.pcitc.szgt.contract.documentinformation.model.Officialdocumentdetailmessage;
import com.pcitc.szgt.contract.documentinformation.model.Officialdocumentmessage;

public interface IDocumentInformationService {

    Callbackdocumentdetail callBackDocument(Officialdocumentdetailmessage officialdocumentdetailmessage);

    String callBackDocumentSave(Officialdocumentdetailmessage officialdocumentdetailmessage);

}
