package ptt.vn.icaremobileapp.api;

import java.util.List;

import ptt.vn.icaremobileapp.model.inpatient.InpatientDomain;

public interface ACallback<T> {
    void response(List<T> list);
}