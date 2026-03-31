# Matriz de Trazabilidad – LogiTrack

La matriz vincula cada historia de usuario del MVP con sus casos de prueba correspondientes y los commits o Pull Requests que implementan la funcionalidad.

## Convenciones

- **Commit/PR**: Se referencia el mensaje del commit o el número del Pull Request que implementa la funcionalidad.
- **Casos de Prueba**: Identificadores de los casos definidos en el Plan de Pruebas (formato TPXX).

## Tabla de trazabilidad

| Historia de Usuario | Caso de Prueba | Evidencia |
|---------------------|----------------|-----------|
| US01 – Registrar envío | TP01, TP02, TP03, TP04 | [#40] LogitrackApp - Implementación de CRUD base de envíos / [#41] LogitrackApp - Implementación de front + US's para MVP |
| US02 – Editar envío (simulado) | TP02 | [#40] LogitrackApp - Implementación de CRUD base de envíos |
| US03 – Cambio de estado | TP05, TP06, TP07, TP08 | [#40] LogitrackApp - Implementación de CRUD base de envíos |
| US04 – Listar envíos | TP11 | [#41] LogitrackApp - Implementación de front + US's para MVP |
| US05 – Buscar envíos | TP12, TP13 | [#41] LogitrackApp - Implementación de front + US's para MVP |
| US06 – Ver historial de estados | TP10, TP15 | [#41] LogitrackApp - Implementación de front + US's para MVP |
| US11 – Consultar estado de envío | TP14, TP15 | [#41] LogitrackApp - Implementación de front + US's para MVP |
| US12 – Manejar estados | TP04, TP05, TP06, TP07, TP08, TP19 | [#40] LogitrackApp - Implementación de CRUD base de envíos |
| US13 – Validar datos ingresados | TP02, TP19 | [#41] LogitrackApp - Implementación de front + US's para MVP |
| US14 – Generar identificador único | TP03 | [#41] LogitrackApp - Implementación de front + US's para MVP |
| US15 – Almacenar historial de estados | TP09, TP10 | [#41] LogitrackApp - Implementación de front + US's para MVP |
| US17 – Registrar timestamp de estado | TP09 | [#41] LogitrackApp - Implementación de front + US's para MVP |
| US20 – Registrar info destinatario | TP01 | [#41] LogitrackApp - Implementación de front + US's para MVP |
| US21 – Registrar dirección entrega | TP01 | [#41] LogitrackApp - Implementación de front + US's para MVP |
| US22 – Registrar fecha estimada | TP01 | [#41] LogitrackApp - Implementación de front + US's para MVP |
| US32 – Prioridad (ML) | TP16, TP18 | [#44] Feature/ml prototype |

---

> Nota: Las evidencias técnicas corresponden a incrementos funcionales del MVP implementados mediante ramas y Pull Requests, siguiendo el flujo de trabajo definido para la Semana 3.

## Notas

- La matriz se mantendrá actualizada a medida que se agreguen nuevas funcionalidades en sprints posteriores.
- Los commits y PRs referenciados corresponden a la implementación del incremento funcional de la Semana 3.

## Referencias

- [Plan de Pruebas](./plan-de-pruebas.md) - Documentación completa de los casos de prueba TP01 al TP20

---

*Última actualización: 31 de marzo de 2026*
