const BASE_URL = 'http://localhost:8080';

export async function getEnvios() {
  const res = await fetch(`${BASE_URL}/envios`);
  if (!res.ok) throw new Error('Error al obtener envíos');
  return res.json();
}

export async function getEnvio(id) {
  const res = await fetch(`${BASE_URL}/envios/${id}`);
  if (!res.ok) throw new Error('Envío no encontrado');
  return res.json();
}

export async function createEnvio(data) {
  const res = await fetch(`${BASE_URL}/envios`, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(data),
  });
  if (!res.ok) throw new Error('Error al crear envío');
  return res.json();
}

export async function updateEnvio(id, data) {
  const res = await fetch(`${BASE_URL}/envios/${id}`, {
    method: 'PUT',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(data),
  });
  if (!res.ok) throw new Error('Error al actualizar envío');
  return res.json();
}

export async function updateEstado(id, estado, fecha, hora, usuario) {
  const res = await fetch(`${BASE_URL}/envios/${id}/estado`, {
    method: 'PUT',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ estado, fecha, hora, usuario }),
  });
  if (!res.ok) {
    const err = await res.json();
    throw new Error(err.error || 'Error al actualizar estado');
  }
  return res.json();
}
