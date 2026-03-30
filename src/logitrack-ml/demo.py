import joblib
import pandas as pd
from datetime import datetime

modelo = joblib.load("model/modelo_prioridad.pkl")

FEATURES = [
    "distancia_km", "tipo_envio", "ventana_horaria", "peso_kg",
    "fragil", "perecedero", "temperatura_controlada",
    "saturacion_ruta", "pago_contra_entrega", "seguro_adicional"
]

envios = [
    {
        "tracking_id": "LT-2026-001",
        "remitente": "Juan Pérez",
        "destinatario": "María González",
        "origen": "Buenos Aires", "destino": "Mendoza",
        "estado_actual": "EN_TRANSITO",
        "fecha_creacion": "2026-03-28 09:00:00",
        # features
        "distancia_km": 1050, "tipo_envio": 0, "ventana_horaria": 0,
        "peso_kg": 10.0, "fragil": 1, "perecedero": 0,
        "temperatura_controlada": 0, "saturacion_ruta": 0.85,
        "pago_contra_entrega": 1, "seguro_adicional": 1,
    },
    {
        "tracking_id": "LT-2026-002",
        "remitente": "Carlos López",
        "destinatario": "Ana Martínez",
        "origen": "Rosario", "destino": "Córdoba",
        "estado_actual": "CREADO",
        "fecha_creacion": "2026-03-29 14:30:00",
        "distancia_km": 400, "tipo_envio": 0, "ventana_horaria": 1,
        "peso_kg": 5.0, "fragil": 0, "perecedero": 0,
        "temperatura_controlada": 0, "saturacion_ruta": 0.3,
        "pago_contra_entrega": 0, "seguro_adicional": 0,
    },
    {
        "tracking_id": "LT-2026-003",
        "remitente": "Lucía Ramírez",
        "destinatario": "Pedro Sánchez",
        "origen": "La Plata", "destino": "Mar del Plata",
        "estado_actual": "EN_SUCURSAL",
        "fecha_creacion": "2026-03-30 08:15:00",
        "distancia_km": 380, "tipo_envio": 1, "ventana_horaria": 0,
        "peso_kg": 12.0, "fragil": 1, "perecedero": 1,
        "temperatura_controlada": 1, "saturacion_ruta": 0.6,
        "pago_contra_entrega": 0, "seguro_adicional": 1,
    },
]

print("-" * 55)
print("   LOGITRACK — Demo de Priorización de Envíos")
print("-" * 55)

for envio in envios:
    X = pd.DataFrame([[envio[f] for f in FEATURES]], columns=FEATURES)
    prioridad = modelo.predict(X)[0]

    print(f"\n   Tracking ID  : {envio['tracking_id']}")
    print(f"   Remitente    : {envio['remitente']}")
    print(f"   Destinatario : {envio['destinatario']}")
    print(f"   Ruta         : {envio['origen']} → {envio['destino']}")
    print(f"   Estado       : {envio['estado_actual']}")
    print(f"   Creado       : {envio['fecha_creacion']}")
    print(f"   Tipo envio   : {envio['tipo_envio']}")
    print(f"   Peso         : {envio['peso_kg']} kg")
    print(f"   Requerimientos: {'Temp. controlada ' if envio['temperatura_controlada'] else ''}{'Frágil ' if envio['fragil'] else ''}{'Perecedero' if envio['perecedero'] else ''}")
    print(f"   Prioridad    : {prioridad}")

print("\n" + "-" * 55)
print("Demo finalizada")
print("-" * 55)