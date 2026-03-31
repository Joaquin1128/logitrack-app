# LogiTrack ML - Módulo de Priorización de Envíos

Módulo de Machine Learning para la clasificación automática de envíos según probabilidad de retraso. Categorías: ALTA, MEDIA y BAJA prioridad.

## Tecnologías
- Python 3.14.3
- scikit-learn
- pandas / numpy

## Cómo ejecutar
```bash
pip install -r requirements.txt
python generate_dataset.py
python train_model.py
python demo.py
```

## Features del modelo
Solo se usan datos operativos del envío (nunca datos personales).

| Feature                | Descripción                                 |
|------------------------|---------------------------------------------|
| distancia_km           | Distancia estimada del envío                |
| tipo_envio             | 0 = estándar, 1 = express                   |
| ventana_horaria        | 0 = mañana, 1 = tarde, 2 = noche            |
| peso_kg                | Peso del paquete (0 a 30 kg)                |
| fragil                 | 0 = no, 1 = sí                              |
| perecedero             | 0 = no, 1 = sí                              |
| temperatura_controlada | 0 = no, 1 = sí                              |
| saturacion_ruta        | Nivel de saturación de la ruta (0.0 a 1.0)  |
| pago_contra_entrega    | 0 = no, 1 = sí                              |
| seguro_adicional       | 0 = no, 1 = sí                              |

## Lógica de prioridad

| Condición                        | Puntaje |
|----------------------------------|---------|
| Distancia > 1500 km              | +2      |
| Tipo express                     | +2      |
| Perecedero                       | +2      |
| Temperatura controlada           | +2      |
| Saturación de ruta > 0.7         | +2      |
| Ventana horaria nocturna         | +1      |
| Peso > 20 kg                     | +1      |
| Frágil                           | +1      |
| Pago contra entrega              | +1      |
| Seguro adicional                 | +1      |

| Puntaje total | Prioridad |
|---------------|-----------|
| >= 6          |   ALTA   |
| >= 3          |   MEDIA  |
| < 3           |   BAJA   |