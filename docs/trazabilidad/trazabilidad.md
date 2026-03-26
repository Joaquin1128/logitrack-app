# Matriz de Trazabilidad – LogiTrack

Este documento vincula las historias de usuario del MVP con los casos de prueba
definidos en la Semana 2 y las evidencias técnicas del repositorio (código, PRs o mocks).

## Trazabilidad funcional

| Historia de Usuario | Caso de Prueba | Evidencia             |
|-------------------- |--------------- |-----------            |  
| US01 – Registrar envío| TP01         | PR Alta de Envío      |
| US02 – Editar envío (simulado)| TP02 | Validaciones de alta  |
| US03 – Cambio de estado | TP05       | PR Cambio de Estado   |
| US12 – Manejar estados | TP06        | Lógica de transiciones|
| US15 – Historial de estados | TP09   | Registro de eventos   |
| US17 – Timestamp de estado | TP09    | EventoDeEstado        |
| US32 – Prioridad (ML) | TP16         | Prototipo ML en Python|


> Nota: Las evidencias técnicas corresponden a incrementos funcionales del MVP
> implementados mediante ramas y Pull Requests, siguiendo el flujo de trabajo
> definido para la Semana 3.

## Pull Requests previstos 

- PR Alta básica de envío (feature/alta-envio)
- PR Cambio de estado e historial (feature/cambio-estado)
- PR Prototipo de prioridad con ML (ml/prototipo-prioridad)
``
