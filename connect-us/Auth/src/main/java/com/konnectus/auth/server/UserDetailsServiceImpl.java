package com.konnectus.auth.server;

import java.util.Arrays;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

@Service(value = "userService")
public class UserDetailsServiceImpl implements UserDetailsService { 
    
    @Autowired
    private MongoClient mongoClient; 
    
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	MongoDatabase database = mongoClient.getDatabase("konnectus");
        MongoCollection<Document> collection = database.getCollection("user");
        Document document = collection.find(Filters.eq("username",username)).first();
        if(document!=null) {
            String email = document.getString("email");
            String password = document.getString("password");
            MongoUser user = new MongoUser(username,password,getAuthority());
           
            return  new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthority());
        }
        throw new UsernameNotFoundException(username);
    }
    
    private List<SimpleGrantedAuthority> getAuthority() {
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
	}

}