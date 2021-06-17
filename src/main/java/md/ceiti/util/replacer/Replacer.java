package md.ceiti.util.replacer;

import md.ceiti.util.exception.DepartmentNotFoundException;
import md.ceiti.util.exception.EmployeeNotFoundException;

public interface Replacer<S, T> {
    void replaceValues(S entityForReplace, T dataForReplace) throws EmployeeNotFoundException, DepartmentNotFoundException;
}
