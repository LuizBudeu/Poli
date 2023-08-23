from metodos import *

# Todas as funções estão definidas em 'metodos.py'

# Roda as opções
def main():
    imprimeCabecalho()
    op = opcoes()
    while op != 'q':
        if op == '1':
            cenarioFicticio()

        elif op == '2':
            cenarioCambio()

        elif op == '3':
            cenarioReal()

        elif op == '4':
            testeTempo()

        else:
            print("ERRO: opção não conhecida. Escolha entre: \'1\', \'2\', \'3\', ou \'q\'")

        op = opcoes()

    print("Tchau!")
    

if __name__ == '__main__': 
    main()



