#language:es
#
# Y te puedo poner todos los comentarios que quiera

Característica: Componente diccionario

  Esquema del escenario: Preguntar por una palabra que existe
    Dado     que tengo un diccionario de "ES"
    Cuando   le pregunto por la palabra "<palabra>"
    Entonces me contesta que "SI" la tiene.

    Ejemplos:
    | palabra |
    | pera    |
    | Pera    |
    | PERA    |
    | manzana |

  Escenario: Preguntar por una palabra que no existe
    Dado     que tengo un diccionario de "ES"
    Cuando   le pregunto por la palabra "archilococo"
    Entonces me contesta que "NO" la tiene.
