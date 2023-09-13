package signin;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class Login extends JFrame {

	private JTextField campoUsuario;
	private JPasswordField senha; // senha
	private JButton login;

	private final String DB_URL = "jdbc:mysql://localhost:3306/farmacia";
	
	private final String DB_USER="root";
	private final String DB_PASSWORD="root";

	
	public Login() {

		setTitle("Faça seu Login!"); // mostra título
		setDefaultCloseOperation(EXIT_ON_CLOSE); //
		setSize(400, 250); // define o tamanho
		
		

		JPanel painel = new JPanel(new GridLayout(5, 2, 10, 10)); //cria uma painel Java
		campoUsuario = new JTextField();
		senha = new JPasswordField();
		login = new JButton("Faça seu Login!");
		painel.add(new JLabel("Usuário:"));
		painel.add(campoUsuario);
		painel.add(new JLabel("Senha:"));
		painel.add(senha);
		painel.add(login);
		
		add(painel);	//cria painel java
		login.addActionListener(new ActionListener() { //salva as informações de entrada, como: 
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String email = campoUsuario.getText();	//usuario
				String ssenha = new String(senha.getPassword());//senha
				String confsenha = new String (senha.getPassword());
					if(!ssenha.equals(confsenha)) { //se  senha for diferente de confirmar senha, então:...
						JOptionPane.showMessageDialog(null, "Senha Incorreta!", "Erro.",JOptionPane.ERROR_MESSAGE);
						return; //ação que permite o retorno da ação para o método anterior
					}
				
			try {
				Connection c = DriverManager.getConnection(DB_URL,  DB_USER, DB_PASSWORD );
				String query = "INSERT INTO usuario(Nome, Usuario, Senha) VALUES (?,?,?)";
				PreparedStatement preparedStatement = c.prepareStatement(query);
				preparedStatement.setString(2, email);
				preparedStatement.setString(3, ssenha);
				preparedStatement.executeUpdate();
				
					JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso!");
				preparedStatement.close();
				
			} catch (Exception e2) {
				System.out.println("Erro!");
				return;
			}	
			}
		});

	}

	public static void main(String[] args) {

		SwingUtilities.invokeLater(() -> { // instanciar um objeto dentro do método principal
			Login login = new Login();// objeto da classe cadastro
			login.setVisible(true);

		});
	}
}
