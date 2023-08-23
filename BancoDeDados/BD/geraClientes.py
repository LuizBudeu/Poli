import random

numeros = "1234567890"
vezes = 10

def geraCPF():
    cpf = ""
    for i in range(11):
        cpf += random.choice(numeros)

    return cpf


def geraRG():
    rg = ""
    for i in range(9):
        rg += random.choice(numeros)

    return rg


def getNo():
    no = ["Maria", "Jose", "Antonio", "Joao", "Francisco", "Ana", "Luiz", "Paulo", "Francisca", "Pedro", "Marcos", "Marcia"]
    return random.choice(no)


def getSn():
    sn = ["Silva", "Santos", "Oliveira", "Souza", "Rodrigues", "Ferreira", "Alves", "Pereira", "Lima", "Gomes", "Costa"]
    return random.choice(sn)


def geraDataNasc():
    ano = random.randint(1965, 2000)
    mes = random.randint(1, 12)
    dia = random.randint(1, 29)
    
    return f"{ano}-{mes}-{dia}"


def geraEndereco():
    ruas = ["Abelha", "Bosque", "Curcuma", "Diulio", "Elizabeth", "Ferdinando", "Gomes", "Hector", "Ilhabela", "Mondizes"]

    return f"Rua {random.choice(ruas)}, {random.randint(10, 1000)}"


def geraCEP():
    rg = ""
    for i in range(8):
        rg += random.choice(numeros)

    return rg


def geraTelefone():
    num = "550"
    num += f"{random.randint(10, 20)}"
    for i in range(8):
        num += random.choice(numeros)

    return num


def geraEmail(nome, sobr):
    return nome + sobr + "@gmail.com"


def geraCliente():
    clientes = []
    for i in range(vezes):
        emp = []
        emp.append(geraCPF())
        emp.append(geraRG())
        no = getNo()
        sn = getSn()
        emp.append(no + " " + sn)
        emp.append(geraDataNasc())
        emp.append(geraEndereco())
        emp.append(geraCEP())
        emp.append(geraTelefone())
        emp.append(geraEmail(no, sn))
        clientes.append(emp)

    return clientes


def geraArquivo():
    f = open("clientes.txt", "w")
    emps = geraCliente()
    for e in emps:
        f.write(f"INSERT INTO cliente (cpf, rg, nome, data_nasc, endereco, cep, telefone, email) values (\"{e[0]}\", \"{e[1]}\", \"{e[2]}\", \"{e[3]}\", \"{e[4]}\", \"{e[5]}\", \"{e[6]}\", \"{e[7]}\");\n")
    f.close()


def main():
    geraArquivo()


if __name__ == '__main__':
    main()