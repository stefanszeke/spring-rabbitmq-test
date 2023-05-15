### `request.html` for testing endpoints with vsCode REST client extension
</br >
## in rabbitMQ manager:
queues:
q.test1
q.test2

## exchanges: </br >

### ex.test.direct
- type: direct
  - bindings: q.test1 routingKey: `rk1`

### ex.test.fanout
- type: fanout
  - bindings: q.test1, q.test2 (no routing keys)

### ex.test.topic
- type: topic
  - binding: q.test1 routingKey: `#.sport`
  - binding: q.test2 routingKey: `#.sport.#`

### ex.test.headers
- type: headers
  - binding: q.test1 arguments: `sport: "true", age: "20"`
  - binding: q.test2 arguments: `x-match: any, sport: "true"`