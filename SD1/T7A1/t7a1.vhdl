library ieee;
use ieee.numeric_bit.all;

entity registrador_universal is
	generic (
		word_size: positive := 4
	);
	port (
		clock, clear, set, enable: in bit;
		control: in bit_vector(1 downto 0);
		serial_input: in bit;
		parallel_input: in bit_vector(word_size-1 downto 0);
		parallel_output: out bit_vector(word_size-1 downto 0)
	);
end entity;

architecture arq of registrador_universal is 

	signal po1, po2: bit_vector (word_size-1 downto 0);
	begin
	po1 <= parallel_input;
	process(clock, clear, set) begin
		if clear = '1' then
			parallel_output <= (others => '0');
		
		elsif set = '1' then
			parallel_output <= (others => '1');
			
		elsif rising_edge(clock) then
			if enable = '1' then
				if control = "11" then 
					parallel_output <= parallel_input;
					po1 <= parallel_input;
				
				elsif control = "01" then -- pra direita
					po2 <= po1;
					po1(word_size-1) <= serial_input;
					for i in word_size-2 downto 0 loop
						po1(i) <= po2(i+1);
					end loop;
				
				elsif control = "10" then -- pra esquerda
					po2 <= po1;
					po1(0) <= serial_input;
					for i in 0 to word_size-2 loop
						po1(i) <= po2(i+1);
					end loop;
					
				end if;

			end if;
		
		end if;
		
	end process;
	
	parallel_output <= po1;
	
end architecture;