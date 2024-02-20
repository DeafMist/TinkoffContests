package alltoscala.stack.task2.client;

import alltoscala.stack.task2.client.model.Address;
import alltoscala.stack.task2.client.model.Event;
import alltoscala.stack.task2.client.model.Payload;
import alltoscala.stack.task2.client.model.Result;

public interface Client {
    //блокирующий метод для чтения данных
    Event readData();

    //блокирующий метод отправки данных
    Result sendData(Address dest, Payload payload);
}
