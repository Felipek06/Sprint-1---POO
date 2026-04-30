public class Main {
    // Separador visual para o console
    private static final String SEPARADOR = "─".repeat(60);

    public static void main(String[] args) {
        System.out.println(SEPARADOR);
        System.out.println("  SISTEMA DE MONITORAMENTO DE VEGETAÇÃO — SPRINT 1");
        System.out.println(SEPARADOR);

        executarTestes();

        System.out.println("\n" + SEPARADOR);
        System.out.println("  FIM DOS TESTES");
        System.out.println(SEPARADOR);
    }

    // Bloco de testes

    private static void executarTestes() {

        // TESTE 1 — Instanciação válida de dois trechos distintos
        titulo("TESTE 1 — Instanciação de trechos válidos");
        try {
            TrechoRodovia trecho1 = new TrechoRodovia(0, 10, 5.0);
            TrechoRodovia trecho2 = new TrechoRodovia(10, 20, 30.0);

            assert trecho1 != null : "trecho1 não deveria ser nulo";
            assert trecho2 != null : "trecho2 não deveria ser nulo";

            System.out.println("Trecho 1 criado: " + trecho1);
            System.out.println("Trecho 2 criado: " + trecho2);
            sucesso("Ambos os trechos instanciados com sucesso.");
        } catch (IllegalArgumentException e) {
            falha("Não deveria lançar exceção aqui. Detalhe: " + e.getMessage());
        }

        // TESTE 2 — registrarCrescimento aumenta o nível corretamente
        titulo("TESTE 2 — Crescimento válido: 10 cm + 5 cm = 15 cm");
        try {
            TrechoRodovia trecho = new TrechoRodovia(0, 5, 10.0);
            trecho.registrarCrescimento(5.0);

            double esperado = 15.0;
            double obtido = trecho.getNivelVegetacaoCm();

            assert obtido == esperado
                    : "Esperado " + esperado + " cm, mas obteve " + obtido + " cm";

            System.out.println("Nível após crescimento: " + obtido + " cm (esperado: " + esperado + " cm)");
            sucesso("Crescimento registrado corretamente.");
        } catch (IllegalArgumentException e) {
            falha("Exceção inesperada: " + e.getMessage());
        }

        // TESTE 3 — Crescimento negativo deve lançar exceção
        titulo("TESTE 3 — Crescimento negativo (-5 cm) deve ser rejeitado");
        try {
            TrechoRodovia trecho = new TrechoRodovia(5, 15, 20.0);
            trecho.registrarCrescimento(-5.0);
            falha("Deveria ter lançado IllegalArgumentException, mas não lançou.");
        } catch (IllegalArgumentException e) {
            sucesso("Exceção capturada corretamente → " + e.getMessage());
        }

        // TESTE 4 — Nível de vegetação inicial negativo deve ser rejeitado
        titulo("TESTE 4 — Nível de vegetação negativo no construtor deve ser rejeitado");
        try {
            new TrechoRodovia(0, 10, -5.0);
            falha("Deveria ter lançado IllegalArgumentException, mas não lançou.");
        } catch (IllegalArgumentException e) {
            sucesso("Exceção capturada corretamente → " + e.getMessage());
        }

        // TESTE 5 — Quilômetro final menor ou igual ao inicial deve ser rejeitado
        titulo("TESTE 5 — KM final <= KM inicial deve ser rejeitado");
        try {
            new TrechoRodovia(20, 10, 0.0); // final < inicial
            falha("Deveria ter lançado IllegalArgumentException, mas não lançou.");
        } catch (IllegalArgumentException e) {
            sucesso("Exceção capturada corretamente → " + e.getMessage());
        }

        // TESTE 6 — Quilômetro inicial negativo deve ser rejeitado
        titulo("TESTE 6 — KM inicial negativo deve ser rejeitado");
        try {
            new TrechoRodovia(-1, 10, 0.0);
            falha("Deveria ter lançado IllegalArgumentException, mas não lançou.");
        } catch (IllegalArgumentException e) {
            sucesso("Exceção capturada corretamente → " + e.getMessage());
        }

        // TESTE 7 — Associação de equipe a trecho crítico
        titulo("TESTE 7 — Associação de equipe a trecho crítico (>= 50 cm)");
        try {
            TrechoRodovia trecho = new TrechoRodovia(30, 50, 45.0);
            trecho.registrarCrescimento(10.0); // sobe para 55 cm → crítico

            System.out.println("Nível atual: " + trecho.getNivelVegetacaoCm() + " cm");
            System.out.println("Trecho crítico? " + trecho.isCritico());

            if (trecho.isCritico()) {
                EquipeManutencao equipe = new EquipeManutencao("Equipe Alpha", 6);
                trecho.associarEquipe(equipe);
                System.out.println("Equipe designada: " + equipe);
            }

            System.out.println("Estado final: " + trecho);
            sucesso("Equipe associada ao trecho crítico com sucesso.");
        } catch (IllegalArgumentException e) {
            falha("Exceção inesperada: " + e.getMessage());
        }

        // TESTE 8 — Equipe com nome vazio deve ser rejeitada
        titulo("TESTE 8 — Equipe com nome vazio deve ser rejeitada");
        try {
            new EquipeManutencao("", 3);
            falha("Deveria ter lançado IllegalArgumentException, mas não lançou.");
        } catch (IllegalArgumentException e) {
            sucesso("Exceção capturada corretamente → " + e.getMessage());
        }

        // TESTE 9 — Equipe com zero integrantes deve ser rejeitada
        titulo("TESTE 9 — Equipe com 0 integrantes deve ser rejeitada");
        try {
            new EquipeManutencao("Equipe Beta", 0);
            falha("Deveria ter lançado IllegalArgumentException, mas não lançou.");
        } catch (IllegalArgumentException e) {
            sucesso("Exceção capturada corretamente → " + e.getMessage());
        }

        // TESTE 10 — Associar equipe nula deve ser rejeitado
        titulo("TESTE 10 — Associar equipe nula deve ser rejeitado");
        try {
            TrechoRodovia trecho = new TrechoRodovia(0, 10, 0.0);
            trecho.associarEquipe(null);
            falha("Deveria ter lançado IllegalArgumentException, mas não lançou.");
        } catch (IllegalArgumentException e) {
            sucesso("Exceção capturada corretamente → " + e.getMessage());
        }
    }

    // Utilitários de exibição
    private static void titulo(String descricao) {
        System.out.println("\n" + SEPARADOR);
        System.out.println("  " + descricao);
        System.out.println(SEPARADOR);
    }

    private static void sucesso(String mensagem) {
        System.out.println("✔ PASSOU  → " + mensagem);
    }

    private static void falha(String mensagem) {
        System.out.println("✘ FALHOU  → " + mensagem);
    }
}