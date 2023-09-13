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

public class Signin extends JFrame {
	
	private JTextField campoNome; // campo de texto swing
	private JTextField campoEmail;
	private JPasswordField senha; // senha
	private JPasswordField confsenha;
	private JButton cadastrese;
	

	private final String DB_URL = "jdbc:mysql://localhost:3306/farmacia";
	
	private final String DB_USER="root";
	private final String DB_PASSWORD="root";

	
	public Signin() {

		setTitle("Cadastre-se agora!"); // mostra título
		setDefaultCloseOperation(EXIT_ON_CLOSE); //
		setSize(400, 250); // define o tamanho
		
		

		JPanel painel = new JPanel(new GridLayout(5, 2, 10, 10)); //cria uma painel Java
		
		campoNome = new JTextField();	//objeto javaTField do campo nome
		campoEmail = new JTextField();
		senha = new JPasswordField();
		confsenha = new JPasswordField();
		cadastrese = new JButton("Cadastre-se aqui!");
		
		painel.add(new JLabel("Nome: "));	// adiciona os objetos criados anteriormente dentro do painel Java 
		painel.add(campoNome);
		painel.add(new JLabel("E-mail: "));
		painel.add(campoEmail);
		painel.add(new JLabel("Senha:"));
		painel.add(senha);
		painel.add(new JLabel("Confirme sua senha:"));
		painel.add(confsenha);
		painel.add(cadastrese);
		
		add(painel);	//cria painel java
		
		
		
		cadastrese.addActionListener(new ActionListener() { //salva as informações de entrada, como: 
			
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String nome = campoNome.getText(); 			//nome
				String email = campoEmail.getText();	//usuario
				String ssenha = new String(senha.getPassword());//senha
				String confSSenha = new String (confsenha.getPassword());
					if(!ssenha.equals(confSSenha)) { //se  senha for diferente de confirmar senha, então:...
						JOptionPane.showMessageDialog(null, "As senhas não coicidem.", "Erro.",JOptionPane.ERROR_MESSAGE);
						return; //ação que permite o retorno da ação para o método anterior
					}
				
			try {
				Connection c = DriverManager.getConnection(DB_URL,  DB_USER, DB_PASSWORD );
				String query = "INSERT INTO usuario(Nome, Email, Senha) VALUES (?,?,?)";
				PreparedStatement preparedStatement = c.prepareStatement(query);
				preparedStatement.setString(1, nome);
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
			Signin cadastro = new Signin();// objeto da classe cadastro
			cadastro.setVisible(true);

		});
	}
}
