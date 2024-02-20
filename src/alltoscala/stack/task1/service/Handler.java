package alltoscala.stack.task1.service;

import alltoscala.stack.task1.dto.ApplicationStatusResponse;

public interface Handler {
    ApplicationStatusResponse performOperation(String id);
}
