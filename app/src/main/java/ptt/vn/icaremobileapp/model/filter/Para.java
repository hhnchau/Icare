package ptt.vn.icaremobileapp.model.filter;

public class Para {
    private String fieldname;
    private int operation;
    private Object value;
    private int typeofvalue;

    public Para(FieldName _fieldname, Operation _operation, DataTypeOfValue _typeofvalue, Object _value) {
        fieldname = _fieldname.name();
        operation = _operation.ordinal();
        value = _value;
        typeofvalue = _typeofvalue.ordinal();
    }
}
