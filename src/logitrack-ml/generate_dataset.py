import pandas as pd
import numpy as np

np.random.seed(42)
n = 500

data = {
    "distancia_km":           np.random.randint(10, 1500, n),
    "tipo_envio":             np.random.choice([0, 1], n),        # 0=estandar, 1=express
    "ventana_horaria":        np.random.choice([0, 1, 2], n),     # 0=mañana, 1=tarde, 2=noche
    "peso_kg":                np.round(np.random.uniform(0.1, 30, n), 1),
    "fragil":                 np.random.choice([0, 1], n),
    "perecedero":             np.random.choice([0, 1], n),
    "temperatura_controlada": np.random.choice([0, 1], n),
    "saturacion_ruta":        np.round(np.random.uniform(0, 1, n), 2),
    "pago_contra_entrega":    np.random.choice([0, 1], n),
    "seguro_adicional":       np.random.choice([0, 1], n),
}

df = pd.DataFrame(data)

def asignar_prioridad(row):
    score = 0
    if row["distancia_km"] > 1500:           score += 2
    if row["tipo_envio"] == 1:              score += 2
    if row["ventana_horaria"] == 2:         score += 1  # noche
    if row["peso_kg"] > 20:                 score += 1
    if row["fragil"] == 1:                  score += 1
    if row["perecedero"] == 1:              score += 2  # urgente por definición
    if row["temperatura_controlada"] == 1:  score += 2  # logística especial
    if row["saturacion_ruta"] > 0.7:        score += 2
    if row["pago_contra_entrega"] == 1:     score += 1  # riesgo de ausencia
    if row["seguro_adicional"] == 1:        score += 1  # valor alto = más cuidado

    if score >= 6:   return "ALTA"
    elif score >= 3: return "MEDIA"
    else:            return "BAJA"

df["prioridad"] = df.apply(asignar_prioridad, axis=1)

df.to_csv("data/dataset.csv", index=False)
print(f"Dataset generado: {len(df)} registros")
print(df["prioridad"].value_counts())