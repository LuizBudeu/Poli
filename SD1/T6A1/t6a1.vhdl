library ieee;
use ieee.numeric_bit.all;

entity gcd_uc is
	port (
		clock, reset: in bit;
		vai, AigualB, AmaiorB: in bit;
		carregaA, carregaB, BmA, sub, fim: out bit
	); 
end entity;

architecture arq of gcd_uc is
	type st_t is (ini, comecou, acabou);
	signal ea, pe: st_t;
	begin
		-- elemento de memória
		process(clock, reset)
		begin
			if reset = '1' then
				ea <= ini;
			elsif rising_edge(clock) then
				ea <= pe;
			end if;
		end process;
		 
		 -- funções de transição e saída
		 eusla: process(ea, vai, AigualB, AmaiorB)
		 begin
			carregaA <= '0'; carregaB <= '0'; Bma <= '0'; sub <= '0'; fim <= '0';
			case(ea) is
				when ini =>
					if vai = '1' then 
						pe <= comecou; 
					end if;
				when comecou =>
					if AigualB = '1' then 
						pe <= acabou;
					else 
						if AmaiorB = '0' then
							BmA <= '1';
							sub <= '1';
							pe <= comecou;
						else 
							sub <= '1';
							BmA <= '0';
							pe <= comecou;
						end if;
					end if;
						
				when acabou =>
					fim <= '1';
					sub <= '0';
					pe <= ini;
					
				when others => 
					pe <= ini;
					
			end case;
				
		end process;
		-- pau no cu do carregaA e carregaB lol
	
end architecture;