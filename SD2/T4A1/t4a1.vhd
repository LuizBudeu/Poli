library ieee;
use ieee.numeric_bit.all;

entity uart_tx is 
    generic(
        clk_freq: positive;
        baudrate: positive;
        addr_depth: natural := 1
    );
    port(
        clk: in bit;
        rst: in bit;
        wdata: in bit_vector(7 downto 0);
        wr: in bit;
        wr_en: out bit;
        tx: out bit
    );
end;

architecture arq of uart_tx is 

    signal contador : integer := -1;
    begin 
    
    process(clk) begin 
    
        if rst = '1' then 
            wr_en <= '0';
            tx <= '1';
            contador <= -1;

        else 
            if rising_edge(clk) then 
                
                if contador > 2**addr_depth then 
                    wr_en <= '0';
                end if;

                if wr = '1' then 
                    if contador = 8 then  -- stop
                        tx <= '1';
                        wr_en <= '1';

                    elsif contador = -1 then -- start
                        tx <= '0';
                        contador <= contador + 1;
                        wr_en <= '1';
                    
                    else  -- data
                        tx <= wdata(contador);
                        contador <= contador + 1;

                    end if;

                end if;

            end if;

        end if;

    end process;
    

end arq;