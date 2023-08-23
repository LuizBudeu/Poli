library ieee;
use ieee.std_logic_1164.all;

entity fluxo_dados_tb is
end entity;

architecture arch of fluxo_dados_tb is

  component fluxo_dados is
    port (
         clock                         : in  std_logic;
         zeraES                        : in  std_logic;
         contaES                       : in  std_logic;
         zeraE                         : in  std_logic;
         contaE                        : in  std_logic;
         zeraTMR                       : in  std_logic;
         contaTMR                      : in  std_logic;
         escreve                       : in  std_logic;
  		   escrevep                      : in  std_logic;
         limpaR                        : in  std_logic;
         registraR                     : in  std_logic;
  		   limpaP                        : in  std_logic;
  		   registraP                     : in  std_logic;
  		   limpaU                        : in  std_logic;
  		   registraU                     : in  std_logic;
  		   limpaFP                       : in  std_logic;
  		   registraFP                    : in  std_logic;
         botoes                        : in  std_logic_vector (3 downto 0);
  		   cargapanico                   : in  std_logic_vector (3 downto 0);
  		   ultimo                        : in  std_logic_vector (3 downto 0);
  		   ultimoPanico                  : in  std_logic_vector (3 downto 0);
         chavesIgualMemoria            : out std_logic;
  		   sPanicoCorreta                : out std_logic;
  		   fimES                         : out std_logic;
         fimE                          : out std_logic;
         fimTMR                        : out std_logic;
         jogada_feita                  : out std_logic;
  		   fimSenha                      : out std_logic;
  		   fimSenhaPanico                : out std_logic;
         db_contagem                   : out std_logic_vector (3 downto 0);
  		   db_memoria                    : out std_logic_vector (3 downto 0);
  		   db_panico                     : out std_logic_vector (3 downto 0);
  		   db_cargap                     : out std_logic_vector (3 downto 0);
  		   db_ultimo                     : out std_logic_vector (3 downto 0);
  		   db_ultimop                    : out std_logic_vector (3 downto 0);
         db_jogada                     : out std_logic_vector (3 downto 0)
    );
  end component;

  signal clock_in                  : std_logic := '0';
  signal zeraES_in                 : std_logic := '0';
  signal contaES_in                : std_logic := '0';
  signal zeraE_in                  : std_logic := '0';
  signal zeraTMR_in                : std_logic := '0';
  signal contaE_in                 : std_logic := '0';
  signal contaTMR_in               : std_logic := '0';
  signal escreve_in                : std_logic := '0';
  signal escrevep_in               : std_logic := '0';
  signal limpaR_in                 : std_logic := '0';
  signal registraR_in              : std_logic := '0';
  signal limpaP_in                 : std_logic := '0';
  signal registraP_in              : std_logic := '0';
  signal limpaU_in                 : std_logic := '0';
  signal registraU_in              : std_logic := '0';
  signal limpaFP_in                : std_logic := '0';
  signal registraFP_in             : std_logic := '0';
  signal botoes_in                 : std_logic_vector (3 downto 0) := "0000";
  signal cargapanico_in            : std_logic_vector (3 downto 0) := "0000";
  signal ultimo_in                 : std_logic_vector (3 downto 0) := "0000";
  signal ultimoPanico_in           : std_logic_vector (3 downto 0) := "0000";
  signal chavesIgualMemoria_out    : std_logic := '0';
  signal sPanicoCorreta_out        : std_logic := '0';
  signal fimES_out                 : std_logic := '0';
  signal fimE_out                  : std_logic := '0';
  signal fimTMR_out                : std_logic := '0';
  signal jogada_feita_out          : std_logic := '0';
  signal fimSenha_out              : std_logic := '0';
  signal fimSenhaPanico_out        : std_logic := '0';
  signal db_contagem_out           : std_logic_vector (3 downto 0) := "0000";
  signal db_memoria_out            : std_logic_vector (3 downto 0) := "0000";
  signal db_panico_out             : std_logic_vector (3 downto 0) := "0000";
  signal db_cargap_out             : std_logic_vector (3 downto 0) := "0000";
  signal db_ultimo_out             : std_logic_vector (3 downto 0) := "0000";
  signal db_ultimop_out            : std_logic_vector (3 downto 0) := "0000";
  signal db_jogada_out             : std_logic_vector (3 downto 0) := "0000";

  -- Configurações do clock
  constant clockPeriod: time := 1 ms;

  -- Identificacao de casos de teste
  signal caso : integer := 0;

begin

  dut: fluxo_dados
    port map(
         clock                => clock_in,
         zeraES               => zeraES_in,
         contaES              => contaES_in,
         zeraE                => zeraE_in,
         contaE               => contaE_in,
         zeraTMR              => zeraTMR_in,
         contaTMR             => contaTMR_in,
         escreve              => escreve_in,
  		   escrevep             => escrevep_in,
         limpaR               => limpaR_in,
         registraR            => registraR_in,
  		   limpaP               => limpaP_in,
  		   registraP            => registraP_in,
  		   limpaU               => limpaU_in,
  		   registraU            => registraU_in,
  		   limpaFP              => limpaFP_in,
  		   registraFP           => registraFP_in,
         botoes               => botoes_in,
  		   cargapanico          => cargapanico_in,
  		   ultimo               => ultimo_in,
  		   ultimoPanico         => ultimoPanico_in,
         chavesIgualMemoria   => chavesIgualMemoria_out
  		   sPanicoCorreta       => sPanicoCorreta_out
  		   fimES                => fimES_out,
         fimE                 => fimE_out,
         fimTMR               => fimTMR_out,
         jogada_feita         => jogada_feita_out,
  		   fimSenha             => fimSenha_out,
  		   fimSenhaPanico       => fimSenhaPanico_out,
         db_contagem          => db_contagem_out,
  		   db_memoria           => db_memoria_out,
  		   db_panico            => db_panico_out,
  		   db_cargap            => db_cargap_out,
  		   db_ultimo            => db_ultimo_out,
  		   db_ultimop           => db_ultimop_out,
         db_jogada            => db_jogada_out
    );

 stimulus: process is
 begin

   assert false report "Inicio da simulacao" severity note;

   ---- condicoes iniciais ----------------
   caso            <= 0;
   clock_in        <= '0';
   zeraES_in       <= '0';
   contaES_in      <= '0';
   zeraE_in        <= '0';
   contaE_in       <= '0';
   zeraTMR_in      <= '0';
   contaTMR_in     <= '0';
   escreve_in      <= '0';
   escrevep_in     <= '0';
   limpaR_in       <= '0';
   registraR_in    <= '0';
   limpaP_in       <= '0';
   registraP_in    <= '0';
   limpaU_in       <= '0';
   registraU_in    <= '0';
   limpaFP_in      <= '0';
   registraFP_in   <= '0';
   botoes_in       <= "0000";
   cargapanico_in  <= "0000";
   ultimo_in       <= "0000";
   ultimoPanico_in <= "0000";
   -- espera por 5 periodos de clock entre testes
   wait for 5*clockPeriod;

   ---- Teste 1 (Registra 0100 no registrador de carga da senha de panico)
   caso <= 1;
   registraP_in   <= '1';
   cargapanico_in <= "0100";
   -- 1 borda de clock
   clock_in   <= '0';
   wait for clockPeriod/2;
   clock_in   <= '1';
   wait for clockPeriod/2;
   clock_in   <= '0';
   -- espera por 2 periodos de clock entre testes
   wait for 2*clockPeriod;

   ---- Teste 2 (Registra 1000 no registrador que armazena a entrada do usuário)
   caso <= 2;
   registraR_in   <= '1';
   botoes_in <= "1000";
   -- 1 borda de clock
   clock_in   <= '0';
   wait for clockPeriod/2;
   clock_in   <= '1';
   wait for clockPeriod/2;
   clock_in   <= '0';
   -- espera por 2 periodos de clock entre testes
   wait for 2*clockPeriod;

   ---- Teste 3 (Registra 0110 no registrador que armazena o tamanho-1 da senha do pânico)
   caso <= 3;
   registraFP_in   <= '1';
   ultimoPanico_in <= "0110";
   -- 1 borda de clock
   clock_in   <= '0';
   wait for clockPeriod/2;
   clock_in   <= '1';
   wait for clockPeriod/2;
   clock_in   <= '0';
   -- espera por 2 periodos de clock entre testes
   wait for 2*clockPeriod;

   ---- Teste 4 (Registra 1001 no registrador que armazena o tamanho-1 da senha normal)
   caso <= 4;
   registraU_in   <= '1';
   ultimo_in <= "1001";
   -- 1 borda de clock
   clock_in   <= '0';
   wait for clockPeriod/2;
   clock_in   <= '1';
   wait for clockPeriod/2;
   clock_in   <= '0';
   -- espera por 2 periodos de clock entre testes
   wait for 2*clockPeriod;

   ---- Teste 5 (Limpa todos os registradores do circuito)
   caso <= 5;
   limpaP_in   <= '1';
   limpaR_in   <= '1';
   limpaFP_in  <= '1';
   limpaU_in   <= '1';
   -- 1 borda de clock
   clock_in   <= '0';
   wait for clockPeriod/2;
   clock_in   <= '1';
   wait for clockPeriod/2;
   clock_in   <= '0';
   -- espera por 2 periodos de clock entre testes
   wait for 2*clockPeriod;

   ---- Teste 6 (Compara os valores do registrador de entrada do usuário (0000) e o dado em 0000 da memória da senha de pânico (0001))
   caso <= 6;
   limpaR_in   <= '0';
   -- espera por 2 periodos de clock entre testes
   wait for 2*clockPeriod;

   ---- Teste 7 (Compara os valores do registrador de entrada do usuário (0001) e o dado em 0000 da memória da senha de pânico (0001))
   caso <= 7;
   botoes_in <= "0001";
   -- 1 borda de clock
   clock_in   <= '0';
   wait for clockPeriod/2;
   clock_in   <= '1';
   wait for clockPeriod/2;
   clock_in   <= '0';
   -- espera por 2 periodos de clock entre testes
   wait for 2*clockPeriod;

   ---- Teste 8 (Compara os valores do registrador de entrada do usuário (0100) e o dado em 0000 da memória da senha normal (0001))
   caso <= 8;
   botoes_in <= "0100";
   -- 1 borda de clock
   clock_in   <= '0';
   wait for clockPeriod/2;
   clock_in   <= '1';
   wait for clockPeriod/2;
   clock_in   <= '0';
   -- espera por 2 periodos de clock entre testes
   wait for 2*clockPeriod;

   ---- Teste 9 (Compara os valores do registrador de entrada do usuário (0001) e o dado em 0000 da memória da senha normal (0001))
   caso <= 9;
   botoes_in <= "0001";
   -- 1 borda de clock
   clock_in   <= '0';
   wait for clockPeriod/2;
   clock_in   <= '1';
   wait for clockPeriod/2;
   clock_in   <= '0';
   -- espera por 2 periodos de clock entre testes
   wait for 2*clockPeriod;

   ---- Teste 10 (Produz 5 bordas de clock com o sinal de contagem do contador de endereço em alto)
   caso <= 10;
   contaE_in   <= '1';
   -- 5 bordas de clock
   for cont in 1 to 5 loop
       clock_in   <= '1';
       wait for clockPeriod/2;
       clock_in   <= '0';
       wait for clockPeriod/2;
   end loop;
   -- espera por 2 periodos de clock entre testes
   wait for 2*clockPeriod;

   ---- Teste 11 (Produz 2 bordas de clock com o sinal de contagem do contador de erros em alto e verifica se terminou em contagem)
   caso <= 11;
   contaE_in   <= '0';
   contaES_in  <= '1';
   -- 2 bordas de clock
   for cont in 1 to 2 loop
       clock_in   <= '1';
       wait for clockPeriod/2;
       clock_in   <= '0';
       wait for clockPeriod/2;
   end loop;
   -- espera por 2 periodos de clock entre testes
   wait for 2*clockPeriod;

   ---- Teste 12 (Produz 3 bordas de clock com o sinal de contagem do contador de erros em alto e verifica se terminou em contagem)
   caso <= 12;
   -- 3 bordas de clock
   for cont in 1 to 3 loop
       clock_in   <= '1';
       wait for clockPeriod/2;
       clock_in   <= '0';
       wait for clockPeriod/2;
   end loop;
   -- espera por 2 periodos de clock entre testes
   wait for 2*clockPeriod;

   ---- Teste 13 (Compara os valores do registrador de endereço (0110) e do registrador de posição final da senha normal (0111))
   caso <= 13;
   limpaU_in  <= '0';
   contaE_in  <= '1';
   ultimo_in  <= "0111";
   -- 1 borda de clock
   clock_in   <= '0';
   wait for clockPeriod/2;
   clock_in   <= '1';
   wait for clockPeriod/2;
   clock_in   <= '0';
   -- espera por 2 periodos de clock entre testes
   wait for 2*clockPeriod;

   ---- Teste 14 (Compara os valores do registrador de endereço (0111) e do registrador de posição final da senha normal (0111))
   caso <= 14;
   registraU_in  <= '0';
   -- 1 borda de clock
   clock_in   <= '0';
   wait for clockPeriod/2;
   clock_in   <= '1';
   wait for clockPeriod/2;
   clock_in   <= '0';
   -- espera por 2 periodos de clock entre testes
   wait for 2*clockPeriod;

   ---- Teste 15 (Compara os valores do registrador de endereço (1000) e do registrador de posição final da senha do pânico (1001))
   caso <= 15;
   limpaFP_in <= '0';
   ultimoPanico_in  <= "1001";
   -- 1 borda de clock
   clock_in   <= '0';
   wait for clockPeriod/2;
   clock_in   <= '1';
   wait for clockPeriod/2;
   clock_in   <= '0';
   -- espera por 2 periodos de clock entre testes
   wait for 2*clockPeriod;

   ---- Teste 16 (Compara os valores do registrador de endereço (1001) e do registrador de posição final da senha do pânico (1001))
   caso <= 16;
   registraFP_in  <= '0';
   -- 1 borda de clock
   clock_in   <= '0';
   wait for clockPeriod/2;
   clock_in   <= '1';
   wait for clockPeriod/2;
   clock_in   <= '0';
   -- espera por 2 periodos de clock entre testes
   wait for 2*clockPeriod;

   ---- Teste 17 (Lê os dados em 1001 de ambas as memórias de senha)
   caso <= 17;
   -- espera por 2 periodos de clock entre testes
   wait for 2*clockPeriod;

   ---- Teste 18 (Lê os dados em 1111 de ambas as memórias de senha)
   caso <= 18;
   -- 6 bordas de clock
   for cont in 1 to 6 loop
       clock_in   <= '1';
       wait for clockPeriod/2;
       clock_in   <= '0';
       wait for clockPeriod/2;
   end loop;
   -- espera por 2 periodos de clock entre testes
   wait for 2*clockPeriod;

   ---- final dos casos de teste  da simulacao
   assert false report "Fim da simulacao" severity note;

   wait; -- fim da simulação
 end process;

end architecture;
