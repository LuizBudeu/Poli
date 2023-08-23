-------------------------------------------------------------------
-- Arquivo   : ram_16x4.vhd
-- Projeto   : Experiencia 2 - Projeto de um Fluxo de Dados Simples
-------------------------------------------------------------------
-- Descricao : módulo de memória RAM sincrona 16x4
--             sinais we e ce ativos em baixo
--             codigo ADAPTADO do código encontrado no livro
--             VHDL Descricao e Sintese de Circuitos Digitais
--             de Roberto D'Amore, LTC Editora.
-------------------------------------------------------------------
-- Revisoes  :
--     Data        Versao  Autor             Descricao
--     08/01/2020  1.0     Edson Midorikawa  criacao
--     01/02/2020  2.0     Antonio V.S.Neto  Atualizacao para
--                                           RAM sincrona para
--                                           minimizar problemas
--                                           com Quartus.
--     02/02/2020  2.1     Edson Midorikawa  revisao de codigo e
--                                           arquitetura para
--                                           simulacao com ModelSim
-------------------------------------------------------------------

library ieee; --importa a biblioteca de descrições ieee
use ieee.std_logic_1164.all;--declara o uso das funções do pacote std_logic_1164
use ieee.numeric_std.all; --declara o uso das funções do pacote numeric_std

entity ram_16x4 is --declara a entidade ram_16x4
   port (      --inicia a descrição de portas da entidade
       clk          : in  std_logic; --declara a entrada do sinal de clock do tipo std_logic
       endereco     : in  std_logic_vector(3 downto 0); --declara a entrada de endereço do tipo std_logic_vector
       dado_entrada : in  std_logic_vector(3 downto 0); --declara a entrada de dados do tipo std_logic_vector
       we           : in  std_logic; --declara a entrada do sinal de escrita do tipo std_logic
       ce           : in  std_logic; --declara a entrada do sinal de habilitação da memória do tipo std_logic
       dado_saida   : out std_logic_vector(3 downto 0) --declara a saída de dados do tipo std_logic_vector
    ); --finaliza a declaração de portas
end entity ram_16x4; --finaliza a declaração da entidade

architecture ram_mif of ram_16x4 is --declara a arquitetura 'ram_mif' da entidade
  type   arranjo_memoria is array(0 to 15) of std_logic_vector(3 downto 0); --declara o tipo interno arranjo_memoria, que representa a organização da memória em um vetor de 16 palavras de 4 bits do tipo std_logic
  signal memoria : arranjo_memoria; --declara o sinal interno memoria do tipo arranjo_memoria, que representa o espaço de memoria interno

  -- Configuracao do Arquivo MIF
  attribute ram_init_file: string; --define o atributo ram_init_file do tipo string
  attribute ram_init_file of memoria: signal is "ram_conteudo_jogadas.mif"; --associa o atributo ram_init_file ao sinal interno memoria e concede-lhe a string "ram_conteudo_jogadas.mif", que é o nome do arquivo que contém o carregamento inicial da memória

begin --começa a descrição da arquitetura

  process(clk) --descreve o funcionamento do circuito, sensível ao clock
  begin --começa a descrição síncrona
    if (clk = '1' and clk'event) then --se ocorre uma borda de subida do clock
          if ce = '0' then -- dado armazenado na subida de "we" com "ce=0"

              -- Detecta ativacao de we (ativo baixo)
              if (we = '0') --se we está baixo
                  then memoria(to_integer(unsigned(endereco))) <= dado_entrada; --escreve o dado de entrada na posição da memória especificada pelo endereço
              end if; --finaliza a condição sobre o sinal de escrita

          end if; --finaliza a condição sobre o sinal de habilitação da memória
      end if; --finaliza a condição sobre o clock
  end process; --finaliza a descrição síncrona

  -- saida da memoria
  dado_saida <= memoria(to_integer(unsigned(endereco))); --leitura assíncrona do dado contido no endereço da memória especificado

end architecture ram_mif; --finaliza a descrição da arquitetura 'ram_mif'

-- Dados iniciais (para simulacao com Modelsim)
architecture ram_modelsim of ram_16x4 is --declara a arquitetura 'ram_modelsim' da entidade
  type   arranjo_memoria is array(0 to 15) of std_logic_vector(3 downto 0); --declara o tipo interno arranjo_memoria, que representa a organização da memória em um vetor de 16 palavras de 4 bits do tipo std_logic
  signal memoria : arranjo_memoria := ( --declara o sinal interno memoria do tipo arranjo_memoria, que representa o espaço de memoria interno, e atribui-lhe um valor inicial
                                        "0001", --posição 0 recebe a palavra "0001"
                                        "0010", --posição 1 recebe a palavra "0010"
                                        "0100", --posição 2 recebe a palavra "0100"
                                        "1000", --posição 3 recebe a palavra "1000"
                                        "0100", --posição 4 recebe a palavra "0100"
                                        "0010", --posição 5 recebe a palavra "0010"
                                        "0001", --posição 6 recebe a palavra "0001"
                                        "0001", --posição 7 recebe a palavra "0001"
                                        "0010", --posição 8 recebe a palavra "0010"
                                        "0010", --posição 9 recebe a palavra "0010"
                                        "0100", --posição 10 recebe a palavra "0100"
                                        "0100", --posição 11 recebe a palavra "0100"
                                        "1000", --posição 12 recebe a palavra "1000"
                                        "1000", --posição 13 recebe a palavra "1000"
                                        "0001", --posição 14 recebe a palavra "0001"
                                        "0100" ); --posição 15 recebe a palavra "0100", fim da atribuição

begin --começa a descrição da arquitetura

  process(clk) --descreve o funcionamento do circuito, sensível ao clock
  begin --começa a descrição síncrona
    if (clk = '1' and clk'event) then --se ocorre uma borda de subida do clock
          if ce = '0' then -- dado armazenado na subida de "we" com "ce=0"

              -- Detecta ativacao de we (ativo baixo)
              if (we = '0') --se we está baixo
                  then memoria(to_integer(unsigned(endereco))) <= dado_entrada; --escreve o dado de entrada na posição da memória especificada pelo endereço
              end if; --finaliza a condição sobre o sinal de escrita

          end if; --finaliza a condição sobre o sinal de habilitação da memória
      end if; --finaliza a condição sobre o clock
  end process; --finaliza a descrição síncrona

  -- saida da memoria
  dado_saida <= memoria(to_integer(unsigned(endereco))); --leitura assíncrona do dado contido no endereço da memória especificado

end architecture ram_modelsim; --finaliza a descrição da arquitetura 'ram_modelsim'
