library ieee;
use ieee.numeric_bit.all;

entity alu is 
    generic(
        size: natural := 10 --bitsize
    );
    port(
        A, B: in bit_vector(size-1 downto 0); --inputs
        F: out bit_vector(size-1 downto 0); -- output
        S: in bit_vector(3 downto 0); --op selection
        Z: out bit; --zero flag
        Ov: out bit; --overflowflag
        Co: out bit --carry out
    );
end entity alu;

architecture arq of alu is 
    signal tempA, tempB, tempFp, tempFm: signed(size-1 downto 0);
    signal temppF, tempmF: bit_vector(size-1 downto 0);
    signal lA, lB, lFp, lFm: bit;

    begin 
    tempA <= signed(A);
    tempB <= signed(B);
    tempFp <= tempA + tempB;
    tempFm <= tempA - tempB;

    temppF <= bit_vector(tempFp);
    tempmF <= bit_vector(tempFm);

    F <= A and B when S = "0000" else
        A or B when S = "0001" else 
        bit_vector(tempFp) when S = "0010" else 
        bit_vector(tempFm) when S = "0110" else 
        (0 => '1', others => '0') when S = "0111" and tempA < tempB else 
        (others => '0') when S = "0111" and tempA > tempB else 
        (others => '0') when S = "0111" and tempA = tempB else 
        (A nor B) when S = "1100" else (others => '0');

    lA <= A(size-1); lB <= B(size-1); lFp <= temppF(size-1); lFm <= tempmF(size-1);

    Ov <= (lFp and not(lA) and not (lB)) or (lA and lB and not(lFp)) when S = "0010" else 
    (lFm and not(lA) and not (lB)) or (not(lA) and lB and not(lFm)) or (lA and not(lB) and not(lFm)) when S = "0110" else '0';

    Z <= '1' when tempFp = 0 or tempFm = 0 else 
        '1' when S = "0111" and tempA > tempB else 
        '1' when S = "0111" and tempA = tempB else '0';

    Co <= (lFp and not(lA) and not (lB)) or (lA and lB and not(lFp)) when S = "0010" else 
    (lFm and not(lA) and not (lB)) or (not(lA) and lB and not(lFm)) or (lA and not(lB) and not(lFm)) when S = "0110" else '0';
        
end arq;


