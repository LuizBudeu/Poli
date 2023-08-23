import random

numeros = "123456789"
vezes = 20
nomes = ["FastBank", "CashCity.io", "BankValue", "Cash Signature", "MoneyBook", "Depositly", "Banksly", "CreditFinancing", "Cashex",
         "Maxpayout", "Banking", "InvestCash", "Coinsure", "Finenco", "ZeroBank", "Exomoney", "Currency River", "PierBank", "CashFunds", "Cricash"]

def geraCNPJ():
    cnpj = ""
    for i in range(14):
        cnpj += random.choice(numeros)

    return cnpj


def geraNome():
    nome = random.choice(nomes)
    nomes.remove(nome)
    return nome


def geraReceita():
    rec = random.choice(numeros)
    zeros = random.randint(5, 8)
    for i in range(zeros):
        rec += "0"

    return rec


def geraLucro():
    lucro = random.choice(numeros)
    zeros = random.randint(4, 7)
    for i in range(zeros):
        lucro += "0"

    return lucro


def geraRoic():
    return random.randint(10, 40)/100


def geraEmpresa():
    empresas = []
    for i in range(vezes):
        emp = []
        emp.append(geraCNPJ())
        emp.append(geraNome())
        emp.append(int(geraReceita()))
        luc = geraLucro()
        while int(luc) >= int(emp[2]):
            luc = geraLucro()
        emp.append(int(luc))
        emp.append(geraRoic())
        empresas.append(emp)

    return empresas


def geraArquivo():
    f = open("empresas.txt", "w")
    emps = geraEmpresa()
    for e in emps:
        f.write(f"INSERT INTO empresa (cnpj, nome, receita, lucro, roic) values (\"{e[0]}\", \"{e[1]}\", {e[2]}, {e[3]}, {e[4]});\n")
    f.close()


def main():
    geraArquivo()


if __name__ == '__main__':
    main()