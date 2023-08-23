-- comentario
entity alarme_tb is
end entity;

architecture teste of alarme_tb is
	component alarme is
		port (
			j0, j1, j2, j3, en0, p: in bit;
			s0: out bit
		);
	end component;
	
	signal j0i, j1i, j2i, j3i, en0i, pi, s0i: bit;
begin
	dut: alarme port map(j0i, j1i, j2i, j3i, en0i, pi, s0i);
	
	meu_teste: process
		begin
			report "BOT";
			
			en0i <= '0'; pi <= '0';
			j0i <= '0'; j1i <= '0'; j2i <= '0'; j3i <= '0';
			wait for 1 ns;
			assert s0i = '0' report "Caso 0 falhou" severity warning;
			
			en0i <= '0'; pi <= '1';
			j0i <= '0'; j1i <= '0'; j2i <= '0'; j3i <= '0';
			wait for 1 ns;
			assert s0i = '1' report "Caso 1 falhou" severity warning;
			
			en0i <= '1'; pi <= '0';
			j0i <= '0'; j1i <= '1'; j2i <= '1'; j3i <= '1';
			wait for 1 ns;
			assert s0i = '1' report "Caso 2 falhou" severity warning;
			
			en0i <= '1'; pi <= '0';
			j0i <= '0'; j1i <= '0'; j2i <= '0'; j3i <= '1';
			wait for 1 ns;
			assert s0i = '1' report "Caso 3 falhou" severity warning;
			
			report "EOT";
			
			wait;
		end process;
	
end architecture;