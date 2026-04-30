package helper;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public final class UtilsUI {

    private UtilsUI() {}

    public static JLabel criarLabelFormula(String formula) {

        JLabel label = new JLabel(
                "<html>" +
                        "<span style='color:red;'><b>Fórmula:</b></span><br>" +
                        "<span style='color:gray;'>" + formula + "</span>" +
                        "</html>"
        );

        label.setFont(new Font("Arial", Font.PLAIN, 12));

        // Borda
        Border dashed = BorderFactory.createDashedBorder(
                Color.LIGHT_GRAY, // cor
                1,                // espessura
                3,                // comprimento do traço
                2,                // espaço entre traços
                false             // cantos arredondados
        );

        // margem
        label.setBorder(BorderFactory.createCompoundBorder(
                dashed,
                BorderFactory.createEmptyBorder(5, 8, 5, 8)
        ));

        return label;
    }
}