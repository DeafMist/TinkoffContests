package alltoscala.stack.task2.client.model;

import java.util.List;

public record Event(List<Address> recipients, Payload payload) {}
