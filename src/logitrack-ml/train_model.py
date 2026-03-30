import pandas as pd
import joblib
from sklearn.ensemble import RandomForestClassifier
from sklearn.model_selection import train_test_split
from sklearn.metrics import classification_report, accuracy_score

df = pd.read_csv("data/dataset.csv")
X = df.drop("prioridad", axis=1)
y = df["prioridad"]

X_train, X_test, y_train, y_test = train_test_split(
    X, y, test_size=0.2, random_state=42
)

modelo = RandomForestClassifier(n_estimators=100, random_state=42)
modelo.fit(X_train, y_train)

y_pred = modelo.predict(X_test)
print("=== MÉTRICAS DEL MODELO ===")
print(f"Accuracy: {accuracy_score(y_test, y_pred):.2f}")
print()
print(classification_report(y_test, y_pred))

joblib.dump(modelo, "model/modelo_prioridad.pkl")
print("Modelo guardado en model/modelo_prioridad.pkl")