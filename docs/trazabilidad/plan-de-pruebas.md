# Plan de Pruebas - LogiTrack

## Propósito

El presente plan de pruebas define los casos necesarios para verificar el funcionamiento del MVP de LogiTrack, cubriendo alta de envíos, validaciones, listado, búsqueda, consulta y cambios de estado con auditoría.

---

## Casos de Prueba

| ID | Funcionalidad | Given | When | Then | US relacionada |
|----|---------------|-------|------|------|----------------|
| TP01 | Alta de envío válida | Un operador en la pantalla de alta | Ingresa todos los datos obligatorios | El sistema crea el envío y asigna un trackingId | US01, US14, US20, US21, US22 |
| TP02 | Validación de datos obligatorios | Un operador en la pantalla de alta | Intenta registrar un envío con datos faltantes | El sistema rechaza la operación y muestra error | US13 |
| TP03 | Tracking ID automático | Un envío válido | Se registra el envío | El sistema genera un trackingId único | US14 |
| TP04 | Estado inicial del envío | Un envío recién creado | Se visualiza el detalle | El estado es "Creado" | US12 |
| TP05 | Cambio de estado (Creado → En tránsito) | Un envío en estado "Creado" | El operador inicia el despacho | El estado cambia a "En tránsito" | US03, US12 |
| TP06 | Flujo de estados (En tránsito → En sucursal) | Un envío en estado "En tránsito" | Se registra su llegada | El estado cambia a "En sucursal" | US03, US12 |
| TP07 | Estado final (En sucursal → Entregado) | Un envío en estado "En sucursal" | Se confirma la entrega | El estado cambia a "Entregado" | US03, US12 |
| TP08 | Cambio de estado inválido | Un envío en estado "Creado" | Se intenta pasar directo a "Entregado" | El sistema rechaza el cambio | US12, US03 |
| TP09 | Registro de evento de estado | Un envío existente | Se cambia su estado | Se registra evento con fecha/hora y usuario | US15, US17 |
| TP10 | Visualización del historial | Un envío con cambios previos | Se consulta el historial | Se muestran eventos en orden cronológico | US06, US15 |
| TP11 | Listado de envíos | Existen envíos registrados | El usuario accede al listado | Se muestran todos los envíos | US04 |
| TP12 | Búsqueda por trackingId | Un trackingId válido | El usuario busca | Se muestra el envío correspondiente | US05 |
| TP13 | Búsqueda sin resultados | Un trackingId inexistente | El usuario busca | El sistema informa "sin resultados" | US05 |
| TP14 | Consulta de estado (cliente) | Un cliente con trackingId válido | Consulta el estado | Se muestra el estado actual | US11 |
| TP15 | Detalle de envío | Un envío existente | Se accede al detalle | Se ven datos, estado e historial | US11, US06 |
| TP16 | Asignación de prioridad simulada | Un envío registrado | Se ejecuta clasificación simulada | Se asigna prioridad Alta/Media/Baja | US32 |
| TP17 | Visualización de prioridad | Un envío con prioridad asignada | Supervisor visualiza el envío | Se muestra la prioridad | US07, US18 |
| TP18 | Persistencia de prioridad | Un envío con prioridad definida | Se vuelve a consultar | La prioridad se mantiene | US32 |
| TP19 | Validación de estados permitidos | El sistema operativo | Se intenta asignar un estado inexistente | El sistema rechaza la operación | US12, US13 |
| TP20 | Bloqueo de cambios tras entrega | Un envío "Entregado" | Se intenta modificar estado | El sistema no permite cambios | US12, US03 |

---

## Criterios de Aceptación por Historia de Usuario

### US01 - Registrar envío
1. Al enviar el formulario con datos válidos, generar un tracking ID único automáticamente.
2. Debe permitir ingresar: remitente, destinatario, origen, destino.
3. La fecha de creación se asigna automáticamente.
4. El estado inicial debe ser "Creado".

### US03 - Actualizar estado de envío
1. El sistema permite cambiar el estado de un envío solo si la transición es válida según el diagrama de estado (Creado → En tránsito → En Sucursal → Entregado).
2. El sistema rechaza cambios de estado inválidos (ej: Creado → Entregado) y muestra un mensaje de error.
3. Una vez que un envío está en estado "Entregado", no se permite modificar su estado.
4. Al cambiar el estado, el sistema registra fecha, hora y usuario responsable.

### US04 - Listar envíos
1. El sistema muestra una lista de todos los envíos registrados.
2. Cada fila muestra al menos: TrackingID, destinatario, estado actual, fecha de creación.
3. La lista está ordenada por fecha de creación descendente (los más recientes primero).

### US05 - Buscar envíos
1. El sistema permite buscar envíos por TrackingID.
2. El sistema permite buscar envíos por nombre de destinatario (búsqueda parcial, insensible a mayúsculas/minúsculas).
3. Si la búsqueda encuentra resultados, se muestra en forma de lista.
4. Si la búsqueda no encuentra resultados, el sistema muestra un mensaje claro "No se encontraron envíos".

### US11 - Consultar estado de envío
1. El cliente puede acceder a una pantalla pública de consulta sin necesidad de autenticación.
2. El cliente ingresa un trackingID válido y el sistema muestra el estado actual del envío.
3. La vista de consulta pública no muestra datos personales completos del destinatario.
4. Si el trackingID no existe, el sistema muestra un mensaje "Número de seguimiento no válido".

### US12 - Manejar estados de envío
1. El sistema reconoce y gestiona los cuatro estados del ciclo de vida del envío: Creado, En tránsito, En sucursal y Entregado.
2. El sistema no permite estados fuera de los cuatro permitidos.

### US13 - Validar datos ingresados
1. El sistema valida que todos los campos obligatorios estén completos antes de crear un envío.
2. El sistema valida que el formato de todos los campos sean válidos (ej: nombre del remitente sin número).
3. Ante cualquier error de validación, el sistema muestra un mensaje específico indicando qué campo tiene el problema.

### US14 - Generar identificador único
1. El sistema genera automáticamente un trackingID único para el nuevo envío.
2. El trackingID tiene un formato predefinido.
3. El sistema garantiza que no existen dos envíos con el mismo trackingID.

### US15 - Almacenar historial de estados
1. Cada vez que se cambia el estado de un envío, el sistema registra el evento.
2. Cada evento contiene: trackingID, estado anterior, nuevo estado, timestamp, usuario.

### US17 - Registrar fecha y hora de cambios de estado
1. El sistema registra automáticamente la fecha y hora (timestamp) en cada cambio de estado.
2. El timestamp se obtiene del servidor, no del cliente, para garantizar precisión.
3. La fecha y hora se muestran en el historial con formato legible (dd/mm/yyyy, HH:MM:SS).

### US20 - Registrar información del destinatario
1. El formulario de alta incluye campos para nombre completo y teléfono de contacto del destinatario.
2. Ambos son campos obligatorios.
3. La información del destinatario se almacena asociada al envío.

### US21 - Registrar dirección de entrega
1. El formulario de alta incluye campos para dirección completa de entrega: calle, número, localidad, provincia, código postal.
2. La dirección de entrega es un campo obligatorio.
3. La dirección se almacena como parte del envío.

### US22 - Registrar fecha estimada de entrega
1. El formulario de alta incluye un campo para fecha estimada de entrega.
2. El sistema valida que la fecha estimada sea posterior a la fecha actual.
3. La fecha estimada se muestra en la lista y en el detalle del envío.
