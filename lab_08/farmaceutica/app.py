import psycopg2

arequipa = psycopg2.connect(
    dbname="almacen_arequipa",
    user="postgres",
    password="postgres",
    host="localhost",
    port="5433"
)

lima = psycopg2.connect(
    dbname="almacen_lima",
    user="postgres",
    password="postgres",
    host="localhost",
    port="5434"
)

def stock(conn):
    cur = conn.cursor()
    cur.execute("SELECT stock FROM inventario WHERE producto='Paracetamol'")
    return cur.fetchone()[0]


# CASO 1: EXITOSO
def transferencia(cantidad):
    cur_a = arequipa.cursor()
    cur_l = lima.cursor()

    try:
        print("\n--- CASO EXITOSO ---")
        print("Arequipa:", stock(arequipa))
        print("Lima:", stock(lima))

        nuevo_a = stock(arequipa) - cantidad
        nuevo_l = stock(lima) + cantidad

        cur_a.execute(
            "UPDATE inventario SET stock=%s WHERE producto='Paracetamol'",
            (nuevo_a,)
        )

        cur_l.execute(
            "UPDATE inventario SET stock=%s WHERE producto='Paracetamol'",
            (nuevo_l,)
        )

        arequipa.commit()
        lima.commit()

        print("TRANSFERENCIA EXITOSA")

    except Exception as e:
        print("ERROR:", e)
        arequipa.rollback()
        lima.rollback()

    finally:
        cur_a.close()
        cur_l.close()


# CASO 2: FALLA

def transferencia_fallida(cantidad):
    cur_a = arequipa.cursor()
    cur_l = lima.cursor()

    try:
        print("\n--- CASO FALLA ---")
        print("Arequipa:", stock(arequipa))
        print("Lima:", stock(lima))

        nuevo_a = stock(arequipa) - cantidad

        cur_a.execute(
            "UPDATE inventario SET stock=%s WHERE producto='Paracetamol'",
            (nuevo_a,)
        )

        print("✔ Arequipa actualizado")

        # SIMULACIÓN DE FALLA
        raise Exception("Nodo Lima no responde")

        cur_l.execute(
            "UPDATE inventario SET stock=%s WHERE producto='Paracetamol'",
            (999,)
        )

        arequipa.commit()
        lima.commit()

    except Exception as e:
        print("ERROR:", e)
        print("ROLLBACK EN AMBOS NODOS")

        arequipa.rollback()
        lima.rollback()

    finally:
        cur_a.close()
        cur_l.close()


# EJECUCIÓN

# transferencia(20)
transferencia_fallida(20)
print("Arequipa:", stock(arequipa))
print("Lima:", stock(lima))