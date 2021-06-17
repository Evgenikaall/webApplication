package md.ceiti.util.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

public class DepartmentNotFoundException extends Exception{
    public DepartmentNotFoundException() {
    }

    public DepartmentNotFoundException(String message) {
        super(message);
    }
}
