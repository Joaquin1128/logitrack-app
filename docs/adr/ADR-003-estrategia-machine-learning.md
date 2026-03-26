# ADR 003 – Estrategia de Machine Learning

## Contexto
El proyecto LogiTrack incluye la incorporación de un prototipo de Machine Learning
para priorizar envíos. En esta etapa no se dispone de datos reales ni se busca una
integración completa con el backend.

## Decisión
El componente de Machine Learning se desarrolla de forma separada utilizando:
- Python
- scikit-learn
- Dataset logístico simulado

El modelo produce una clasificación de prioridad (Alta, Media, Baja).

## Consecuencias
- Permite experimentar con ML sin bloquear el desarrollo del sistema principal
- Simplifica el desarrollo del MVP
- La integración en tiempo real se posterga para etapas futuras
``
