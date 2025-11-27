#language: es
Característica: Reproducir una cancion en youtube.
  Como usuario
  Quiero buscar una cancion en youtube
  Para reproducirla.

  Esquema del escenario: reproduccion exitosa.
    Dado que el usuario abre el navegador en la pagina de youtube
    Cuando el usuario ingresa el nombre de la cancion y presione enter
      | nombreCancion   |
      | <nombreCancion> |
    Entonces el usuario podra reproducir una cancion.
      | nombreCancion   |
      | <nombreCancion> |

    Ejemplos:
      | nombreCancion |
      | In the End    |