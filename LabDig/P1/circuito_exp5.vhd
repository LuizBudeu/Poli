library ieee;
use ieee.std_logic_1164.all;

entity circuito_exp5 is
 port (
      clock                     : in std_logic;
      reset                     : in std_logic;
      iniciar                   : in std_logic; --jogar
      botoes                    : in std_logic_vector(3 downto 0);
      leds                      : out std_logic_vector(3 downto 0);
      pronto                    : out std_logic;
      ganhou                    : out std_logic;
      perdeu                    : out std_logic;
      db_contagem               : out std_logic_vector(6 downto 0);
      db_memoria                : out std_logic_vector(6 downto 0);
      db_sequencia              : out std_logic_vector(6 downto 0);
      db_estado                 : out std_logic_vector(6 downto 0);
      db_jogada                 : out std_logic_vector(6 downto 0);
      db_jogada_feita           : out std_logic;
      db_enderecoIgualSequencia : out std_logic;
      db_chavesIgualMemoria     : out std_logic
 );
end entity;

architecture arch of circuito_exp5 is

  signal db_int_memoria         : std_logic_vector(3 downto 0);
  signal db_int_jogada          : std_logic_vector(3 downto 0);
  signal db_int_sequencia       : std_logic_vector(3 downto 0);
  signal db_int_contagem        : std_logic_vector(3 downto 0);
  signal db_int_estado          : std_logic_vector(4 downto 0);
  signal enderecoIgualSequencia : std_logic;
  signal chavesIgualMemoria     : std_logic;
  signal zeraS                  : std_logic;
  signal contaS                 : std_logic;
  signal zeraE                  : std_logic;
  signal contaE                 : std_logic;
  signal zeraTMR                : std_logic;
  signal contaTMR               : std_logic;
  signal limpaR                 : std_logic;
  signal registraR              : std_logic;
  signal limpaM                 : std_logic;
  signal registraM              : std_logic;
  signal fimS                   : std_logic;
  signal fimTMR                 : std_logic;
  signal jogada_feita           : std_logic;

  component estado7seg is
      port (
          estado : in  std_logic_vector(4 downto 0);
          sseg   : out std_logic_vector(6 downto 0)
      );
  end component;

  component hexa7seg is
      port (
          hexa : in  std_logic_vector(3 downto 0);
          sseg : out std_logic_vector(6 downto 0)
      );
  end component;

  component fluxo_dados is
    port (
         clock                         : in  std_logic;
         zeraS                         : in  std_logic;
         contaS                        : in  std_logic;
         zeraE                         : in  std_logic;
         contaE                        : in  std_logic;
         zeraTMR                       : in  std_logic;
         contaTMR                      : in  std_logic;
         escreve                       : in  std_logic;
         limpaR                        : in  std_logic;
         registraR                     : in  std_logic;
         limpaM                        : in  std_logic;
         registraM                     : in  std_logic;
         botoes                        : in  std_logic_vector (3 downto 0);
         chavesIgualMemoria            : out std_logic;
         enderecoIgualSequencia        : out std_logic;
         enderecoMenorOuIgualSequencia : out std_logic;
         fimE                          : out std_logic;
         fimS                          : out std_logic;
         fimTMR                        : out std_logic;
         jogada_feita                  : out std_logic;
         db_contagem                   : out std_logic_vector (3 downto 0);
         db_sequencia                  : out std_logic_vector (3 downto 0);
         db_memoria                    : out std_logic_vector (3 downto 0);
         db_jogada                     : out std_logic_vector (3 downto 0)
    );
  end component;

  component unidade_controle is
     port (
         clock                  : in  std_logic; --
         reset                  : in  std_logic; --
         jogar                  : in  std_logic; -- iniciar
         fimTMR                 : in  std_logic; --
         fimS                   : in  std_logic; --
         enderecoIgualSequencia : in  std_logic; --
         chavesIgualMemoria     : in  std_logic; --
         jogada_feita           : in  std_logic; --
         zeraS                  : out std_logic; --
         zeraE                  : out std_logic; --
         zeraTMR                : out std_logic; --
         limpaM                 : out std_logic; --
         limpaR                 : out std_logic; --
         registraM              : out std_logic; --
         registraR              : out std_logic; --
         contaTMR               : out std_logic; --
         contaE                 : out std_logic; --
         contaS                 : out std_logic; --
         pronto                 : out std_logic;
         perdeu                 : out std_logic;
         ganhou                 : out std_logic;
         db_estado              : out std_logic_vector(4 downto 0)
     );
  end component;

begin

   FD: fluxo_dados
   port map(clock, zeraS, contaS, zeraE, contaE, zeraTMR, contaTMR, '0', limpaR, registraR, limpaM, registraM, botoes, chavesIgualMemoria, enderecoIgualSequencia, open, open, fimS, fimTMR, jogada_feita, db_int_contagem, db_int_sequencia, db_int_memoria, db_int_jogada);

   UC: unidade_controle
   port map(clock, reset, iniciar, fimTMR, fimS, enderecoIgualSequencia, chavesIgualMemoria, jogada_feita, zeraS, zeraE, zeraTMR, limpaM, limpaR, registraM, registraR, contaTMR, contaE, contaS, pronto, perdeu, ganhou, db_int_estado);

   HEXestado: estado7seg
   port map(db_int_estado, db_estado);

   HEXcontagem: hexa7seg
   port map(db_int_contagem, db_contagem);

   HEXsequencia: hexa7seg
   port map(db_int_sequencia, db_sequencia);

   HEXjogada: hexa7seg
   port map(db_int_jogada, db_jogada);

   HEXmemoria: hexa7seg
   port map(db_int_memoria, db_memoria);

   leds <= db_int_memoria;
   db_enderecoIgualSequencia <= enderecoIgualSequencia;
   db_chavesIgualMemoria <= chavesIgualMemoria;
   db_jogada_feita <= jogada_feita;

end architecture;
