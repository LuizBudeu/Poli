import random
from geraEmpresas import nomes

numeros = "123456789"
letras = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"


def geraCodigo():
    codigo = ""
    for i in range(5):
        codigo += random.choice(letras)

    return codigo

def geraIDTipo():
    return random.randint(1, 3)

def pegaCNPJs():
    cnpjs = []
    f = open("empresas.txt", "r")
    for line in f:
        cnpj = ""
        for a in line:
            if a.isdigit():
                cnpj += a
        cnpj = cnpj[0:14]
        cnpjs.append(cnpj)
    f.close()

    return cnpjs

def geraCNPJAleatorio():
    cnpjs = pegaCNPJs()
    return random.choice(cnpjs)

def geraAcao():
    acao = []
    cnpjs = pegaCNPJs()

    for i in range(3):
        for a in cnpjs:
            emp2 = []
            emp2.append(geraCodigo())
            emp2.append(i+1)
            emp2.append(a)

            acao.append(emp2)

    return acao


def geraArquivo():
    f = open("acao.txt", "w")
    emps = geraAcao()
    for e in emps:
        f.write(f"INSERT INTO acao (codigo, id_tipo, cnpj_empresa) values (\"{e[0]}\", {e[1]}, \"{e[2]}\");\n")
    f.close()


def main():
    geraArquivo()


if __name__ == '__main__':
    main()





