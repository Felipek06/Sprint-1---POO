public class TrechoRodovia {
    private int quilometroInicial;
    private int quilometroFinal;
    private double nivelVegetacaoCm;

    public TrechoRodovia(int quilometroInicial, int quilometroFinal, String nivelVegetacaoCm){
        this.quilometroInicial = 0;
        this.quilometroFinal = 0;
        this.nivelVegetacaoCm = 0;
    }

    public void registrarQuilometroFina(int registroQuilometroFinal){
        if (registroQuilometroFinal <= 0){
            System.out.println("O registro de quilometro final das rodovias não pode ser nulo ou negativo");
        } else if (registroQuilometroFinal == quilometroInicial) {
            System.out.println("O registro de quilometro final das rodovias não pode ser igual ao quilometro inicial");
        } else{
            quilometroFinal =  registroQuilometroFinal;
        }
    }

    public void registrarQuilometroInical(int registroQuilometroInicial){
        //Rodovias no Brasil se iniciam no 0
        if (registroQuilometroInicial < 0){
            System.out.println("O registro de quilometro inical das rodovias não pode ser negativo");
        }
        else{
            quilometroInicial =  registroQuilometroInicial;
        }
    }


    public void registrarCrescimentoCm(double taxaCm){
        if (taxaCm <= 0){
            System.out.println("a taxa de cresimento da vegetação não pode ser negativa ou nula");
        }
        else{
            nivelVegetacaoCm = nivelVegetacaoCm + taxaCm;
        }
    }

    public int getQuilometroInicial() {
        return quilometroInicial;
    }

    public int getQuilometroFinal() {
        return quilometroFinal;
    }

    public double getNivelVegetacaoCm() {
        return nivelVegetacaoCm;
    }
}
