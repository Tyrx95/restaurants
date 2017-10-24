import Ember from 'ember';
import BaseAuthenticator from 'ember-simple-auth/authenticators/base';

export default BaseAuthenticator.extend({
  restore(data) {
    return new Ember.RSVP.Promise((resolve, reject) => {
      if (!Ember.isEmpty(data.token)) {
        resolve(data);
      } else {
        reject();
      }
    });
  },
  authenticate(credentials) {
    return new Ember.RSVP.Promise((resolve, reject) => {
      Ember.$.ajax({
        headers: {
          'Content-Type': 'application/json'
          //'Authorization': 'Basic ' + btoa(credentials.email + ':' + credentials.password)
        },
        url: 'http://localhost:8080/login',
        type: 'POST',
        data: JSON.stringify({
          email: credentials.email,
          password: credentials.password
        })
      }).done(function (data, textStatus, xhr) {
        Ember.run(function() {
          resolve({
              token: xhr.getResponseHeader('x-auth-token')
            });
        });
      }).fail(function(xhr, textStatus, errorThrown) {
        Ember.run(function() {
          if(xhr.status === 0) {
            reject('Please check your internet connection!');
          } else {
            reject(xhr.responseJSON.message);
          }
        });
      });
    });
  },
  invalidate(data) {
    return new Ember.RSVP.Promise((resolve, reject) => {
      Ember.$.ajax({
        headers: {
          'Content-Type': 'application/json',
          'x-auth-token': data.token,
        },
        url: 'https://localhost:8080/logout',
        type: 'GET'
      }).done(function (data, textStatus, xhr) {
        Ember.run(function() {
          resolve();
        });
      }).fail(function(xhr, textStatus, erroLoginrThrown) {
        Ember.run(function() {
          //reject();
          resolve();
        });
      });
    });
  }
});
