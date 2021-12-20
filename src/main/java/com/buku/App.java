package com.buku;

import com.buku.repository.AccountRepository;
import com.buku.repository.impl.AccountRepositoryImpl;
import com.buku.service.AccountService;
import com.buku.service.impl.AccountServiceImpl;
import com.buku.utils.Database;
import com.buku.view.LoginView;
import com.buku.view.MenuView;

import javax.swing.*;

public class App {

    public static void main(String[] args) {
        AccountRepository accountRepository = new AccountRepositoryImpl(Database.getConnection());
        AccountService accountService = new AccountServiceImpl(accountRepository);
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new LoginView(accountService).setVisible(true));
    }
    
}
