
library ieee;
use ieee.std_logic_1164.all;

entity unidade_controle is
   port (
       clock                  : in  std_logic;
       reset                  : in  std_logic;
       jogar                  : in  std_logic;
       fimTMR                 : in  std_logic;
       fimS                   : in  std_logic;
       enderecoIgualSequencia : in  std_logic;
       chavesIgualMemoria     : in  std_logic;
       jogada_feita           : in  std_logic;
       zeraS                  : out std_logic;
       zeraE                  : out std_logic;
       zeraTMR                : out std_logic;
       limpaM                 : out std_logic;
       limpaR                 : out std_logic;
       registraM              : out std_logic;
       registraR              : out std_logic;
       contaTMR               : out std_logic;
       contaE                 : out std_logic;
       contaS                 : out std_logic;
       pronto                 : out std_logic;
       perdeu                 : out std_logic;
       ganhou                 : out std_logic;
       db_estado              : out std_logic_vector(4 downto 0)
   );
end entity;

architecture fsm of unidade_controle is
    type t_estado is (inicial, preparacao, inicio_rodada, carrega, mostra, desliga_leds, proxima_mostra, leds_desligados, preparacao_jogada, espera, registra_jogada, comparacao, proximo, fim_erro, verifica_fim, proxima_sequencia, fim_acerto);
    signal Eatual, Eprox: t_estado;
begin

    -- memoria de estado
    process (clock,reset)
    begin
        if reset='1' then
            Eatual <= inicial;
        elsif clock'event and clock = '1' then
            Eatual <= Eprox;
        end if;
    end process;

    -- logica de proximo estado
    Eprox <=
        inicial               when  Eatual=inicial and jogar='0' else
        preparacao            when  Eatual=inicial and jogar='1' else
        inicio_rodada         when  Eatual=preparacao else
        carrega               when  Eatual=inicio_rodada else
        mostra                when  Eatual=carrega else
        mostra                when  Eatual=mostra and fimTMR='0' else
        desliga_leds          when  Eatual=mostra and fimTMR='1' else
        proxima_mostra        when  Eatual=desliga_leds and enderecoIgualSequencia='0' else
        preparacao_jogada     when  Eatual=desliga_leds and enderecoIgualSequencia='1' else
        leds_desligados       when  Eatual=proxima_mostra else
        leds_desligados       when  Eatual=leds_desligados and fimTMR='0' else
        carrega               when  Eatual=leds_desligados and fimTMR='1' else
        espera                when  Eatual=preparacao_jogada else
        espera                when  Eatual=espera and jogada_feita='0' else
        registra_jogada       when  Eatual=espera and jogada_feita='1' else
        comparacao            when  Eatual=registra_jogada else
        fim_erro              when  Eatual=comparacao and chavesIgualMemoria='0' else
        proximo               when  Eatual=comparacao and chavesIgualMemoria='1' and enderecoIgualSequencia='0' else
        verifica_fim          when  Eatual=comparacao and chavesIgualMemoria='1' and enderecoIgualSequencia='1' else
        espera                when  Eatual=proximo else
        proxima_sequencia     when  Eatual=verifica_fim and fimS='0' else
        fim_acerto            when  Eatual=verifica_fim and fimS='1' else
        inicio_rodada         when  Eatual=proxima_sequencia else
        fim_erro              when  Eatual=fim_erro and jogar='0' else
        fim_acerto            when  Eatual=fim_acerto and jogar='0' else
        preparacao            when  (Eatual=fim_erro or Eatual=fim_acerto) and jogar='1' else
        inicial;

    -- logica de saída (maquina de Moore)
    with Eatual select
         zeraS <=     '1' when preparacao,
                      '0' when others;

    with Eatual select
         zeraE <=     '1' when inicio_rodada | preparacao_jogada,
                      '0' when others;

    with Eatual select
         zeraTMR <=   '1' when inicio_rodada | carrega | proxima_mostra,
                      '0' when others;

    with Eatual select
         limpaM <=    '1' when inicial | desliga_leds,
                      '0' when others;

    with Eatual select
         limpaR <=    '1' when inicial | preparacao_jogada | proxima_sequencia,
                      '0' when others;

    with Eatual select
         registraM <= '1' when carrega,
                      '0' when others;

    with Eatual select
         registraR <= '1' when registra_jogada,
                      '0' when others;

    with Eatual select
         contaTMR <=  '1' when mostra | leds_desligados,
                      '0' when others;

    with Eatual select
         contaE <=    '1' when proxima_mostra | proximo,
                      '0' when others;

    with Eatual select
         contaS <=    '1' when proxima_sequencia,
                      '0' when others;

    with Eatual select
         pronto <=    '1' when fim_erro | fim_acerto,
                      '0' when others;

    with Eatual select
         perdeu <=    '1' when fim_erro,
                      '0' when others;

    with Eatual select
         ganhou <=    '1' when fim_acerto,
                      '0' when others;


    -- saida de depuracao (db_estado)
    with Eatual select
        db_estado <= "00000" when inicial,     -- 0
                     "00001" when preparacao,  -- 1
                     "00010" when inicio_rodada, -- 2
                     "00011" when carrega,     -- 3
                     "00100" when mostra,      -- 4
                     "00101" when desliga_leds,  -- 5
                     "00110" when proxima_mostra, -- 6
                     "00111" when leds_desligados, -- 7
                     "01000" when preparacao_jogada, -- 8
                     "01001" when espera,      -- 9
                     "01010" when registra_jogada, -- A (10)
                     "01011" when comparacao,  -- B (11)
                     "01100" when proximo,     -- C (12)
                     "01101" when verifica_fim, -- D (13)
                     "01110" when proxima_sequencia, -- E (14)
                     "01111" when fim_acerto,  -- F (15)
                     "10000"  when fim_erro,    -- 10/- (16)
                     "10011"  when others;      -- 13/_ (19)

end fsm;
