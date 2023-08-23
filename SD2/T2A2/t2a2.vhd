library ieee;
use ieee.numeric_bit.all;
use ieee.math_real.ceil;
use ieee.math_real.log2;

entity regfile is 
    generic(
        regn: natural := 32;
        wordSize: natural := 64
    );
    port(
        clock: in bit;
        reset: in bit;
        regWrite: in bit;
        rr1, rr2, wr: in bit_vector(natural(ceil(log2(real(regn))))-1 downto 0);
        d: in bit_vector(wordSize-1 downto 0);
        q1, q2: out bit_vector(wordSize-1 downto 0)
    );
end regfile;

architecture arq of regfile is 

    type mem_tipo is array (0 to regn-1) of bit_vector(wordSize-1 downto 0);
    signal banco: mem_tipo;

    begin
        process(clock, reset) begin
            if reset = '1' then
                banco <= (others => (others => '0'));

            elsif rising_edge(clock) then 
                if regWrite = '1' then 
                    if unsigned(wr) /= regn-1 then
                        banco(to_integer(unsigned(wr))) <= d;
                    end if;
                
                end if;

            end if;

        end process;

        q1 <= banco(to_integer(unsigned(rr1)));
        q2 <= banco(to_integer(unsigned(rr2)));

end arq;