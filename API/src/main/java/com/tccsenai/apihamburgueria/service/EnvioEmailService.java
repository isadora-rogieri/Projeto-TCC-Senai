package com.tccsenai.apihamburgueria.service;
import com.tccsenai.apihamburgueria.dto.EmailDto;
import com.tccsenai.apihamburgueria.model.Pedido;
import com.tccsenai.apihamburgueria.model.Usuario;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;


@Slf4j
@Service
public class EnvioEmailService {


    @Autowired
    private JavaMailSenderImpl javaMailSender;

    @Value("${support.mail}")
    private String supportMail;

    public void constroeEmail(EmailDto emailDto) throws MessagingException {

        MimeMessage email = javaMailSender.createMimeMessage();

        MimeMessageHelper menssagem = new MimeMessageHelper(email);
        menssagem.setTo(emailDto.getPara());
        menssagem.setSubject(emailDto.getAssunto());
        menssagem.setText(emailDto.getMensagem());
        menssagem.setFrom(supportMail);
        javaMailSender.send(email);

    }


    public void enviaEmail(Pedido pedido) throws MessagingException {

        final String mensagem = this.criarMensagem(pedido);

        final EmailDto sendEmailDto = EmailDto.builder()
                .assunto("Novo Pedido: " + pedido.getId())
                .para("hamburgueria.projetotcc.senai@gmail.com")
                .mensagem(mensagem)
                .build();

        constroeEmail(sendEmailDto);

    }

    private String criarMensagem(final Pedido pedido) {
        final Usuario usuario = pedido.getUsuario();

        final StringBuilder mensagem = new StringBuilder();
        mensagem.append("Cliente:" + usuario.getNome() + "\nDados entrega: "
         + usuario.getEndereco() + " - " + usuario.getNumero() + " - " + usuario.getBairro()
                +  " - " + usuario.getCidade()
                +   " - " + usuario.getCep()).append("\n");
        mensagem.append("Pedido: ").append(pedido.getId()).append("\n");
        mensagem.append("Produtos: ").append("\n");
        pedido.getItens().forEach(item -> {
            mensagem.append(item.getQuantidade()).append(" unidades - ").append(item.getProduto().getNome()).append("\n");
        });
        mensagem.append("Forma de pagamento: " + pedido.getFormaPagamento());

        return mensagem.toString();
    }
}
