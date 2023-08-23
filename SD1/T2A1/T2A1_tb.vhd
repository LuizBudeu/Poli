entity T2A1_tb is
end entity;

architecture teste of T2A1_tb is
	component gray2bin is
		port (
		gray2, gray1, gray0: in bit;
		bin2, bin1, bin0: out bit
	);
	end component;
	signal gray2I, gray1I, gray0I, bin2s, bin1s, bin0s: bit;
	
	begin
		dut: gray2bin port map (gray2I, gray1I, gray0I, bin2s, bin1s, bin0s);
		teste: process
		begin
			report "BOT";
			
			gray2I <= '0'; gray1I <= '0'; gray0I <= '0';
			wait for 1 ns;
			assert bin2s = '0' report "Caso 1.2 falhou!" severity warning;
			assert bin1s = '0' report "Caso 0.1 falhou!" severity warning;
			assert bin0s = '0' report "Caso 0.0 falhou!" severity warning;
			
			report "EOT";
			wait;
		end process;
		
	end teste;