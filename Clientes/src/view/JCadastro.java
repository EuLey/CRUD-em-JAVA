package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.DAO;
import model.Cliente;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.TextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JCadastro extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldNome;
	private JTextField textFieldCpfCnpj;
	private JTextField textFieldTelefone;
	private JLabel lblEndereo;
	private JTextField textFieldEmail;
	private JLabel lblEndereo_1;
	private JTextArea textAreaEndereco;  
	private JButton btnNewButton_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JCadastro frame = new JCadastro(null, null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public JCadastro(Cliente clienteSelecionado, JPrincipal jPricipal) {
		DAO dao = new DAO();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(128, 128, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nome");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(10, 11, 89, 14);
		contentPane.add(lblNewLabel);
		
		textFieldNome = new JTextField();
		textFieldNome.setBounds(10, 28, 397, 20);
		contentPane.add(textFieldNome);
		textFieldNome.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("CPF / CNPJ");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(10, 59, 70, 14);
		contentPane.add(lblNewLabel_1);
		
		textFieldCpfCnpj = new JTextField();
		textFieldCpfCnpj.setBounds(10, 84, 191, 20);
		contentPane.add(textFieldCpfCnpj);
		textFieldCpfCnpj.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Telefone");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_2.setBounds(211, 59, 89, 14);
		contentPane.add(lblNewLabel_2);
		
		textFieldTelefone = new JTextField();
		textFieldTelefone.setColumns(10);
		textFieldTelefone.setBounds(211, 84, 196, 20);
		contentPane.add(textFieldTelefone);
		
		lblEndereo = new JLabel("E-mail");
		lblEndereo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEndereo.setBounds(10, 115, 70, 14);
		contentPane.add(lblEndereo);
		
		textFieldEmail = new JTextField();
		textFieldEmail.setColumns(10);
		textFieldEmail.setBounds(10, 132, 397, 20);
		contentPane.add(textFieldEmail);
		
		lblEndereo_1 = new JLabel("Endereço");
		lblEndereo_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEndereo_1.setBounds(10, 163, 70, 14);
		contentPane.add(lblEndereo_1);
		
		textAreaEndereco = new JTextArea();
		textAreaEndereco.setBounds(10, 188, 397, 28);
		contentPane.add(textAreaEndereco);
		
		JButton btnNewButton = new JButton(clienteSelecionado == null? "Incluir": "Alterar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
			//String id, String nome, String cpfCnpj, String email, String telefone, String endereço
			
			Cliente cliente = new Cliente(null, textFieldNome.getText(), textFieldCpfCnpj.getText(),
					textFieldEmail.getText(), textFieldTelefone.getText(), textAreaEndereco.getText());
			
					if(clienteSelecionado == null) {
					if(!"".equalsIgnoreCase(textFieldNome.getText()) && !"".equalsIgnoreCase(textFieldCpfCnpj.getText())){
						dao.cadastrarCliente(cliente);
						abrirTelaPrincipal(jPricipal);	
			}else {
				JOptionPane.showMessageDialog(null, "Confira os campos Nome e CPF/CNPJ");
			}

			}else {
				if(!"".equalsIgnoreCase(textFieldNome.getText()) && !"".equalsIgnoreCase(textFieldCpfCnpj.getText())){
					dao.alterarCliente(clienteSelecionado.getId(), cliente);
					abrirTelaPrincipal(jPricipal);
		}else {
			JOptionPane.showMessageDialog(null, "Confira os campos Nome e CPF/CNPJ");
					}
			
				}
		
			}
			
		});
		btnNewButton.setBounds(318, 227, 89, 23);
		contentPane.add(btnNewButton);
		
		btnNewButton_1 = new JButton("Excluir");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dao.excluirCliente(clienteSelecionado.getId());
				abrirTelaPrincipal(jPricipal);
			}
		});
		btnNewButton_1.setForeground(new Color(255, 0, 0));
		btnNewButton_1.setBounds(10, 227, 89, 23);
		btnNewButton_1.setVisible(false);
		contentPane.add(btnNewButton_1);
		
		
		if(clienteSelecionado!=null) {
		preencherCampos(clienteSelecionado);
		btnNewButton_1.setVisible(true);
		}
	}
	
	private void preencherCampos(Cliente clienteSelecionado) {
		textFieldNome.setText(clienteSelecionado.getNome());
		textFieldCpfCnpj.setText(clienteSelecionado.getCpfCnpj());
		textFieldEmail.setText(clienteSelecionado.getEmail());
		textFieldTelefone.setText(clienteSelecionado.getTelefone());
		textAreaEndereco.setText(clienteSelecionado.getEndereço());
	}
	
	private void abrirTelaPrincipal(JPrincipal jPrincipal) {
		jPrincipal.dispose();
		dispose();
		jPrincipal = new JPrincipal();
		jPrincipal.setLocationRelativeTo(jPrincipal);
		jPrincipal.setVisible(true);
		
	}
}
