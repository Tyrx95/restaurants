import Ember from 'ember';
import BaseAuthenticator from 'ember-simple-auth/authenticators/base';

export default BaseAuthenticator.extend({
  restore(data) {
    return new Ember.RSVP.Promise((resolve, reject) => {
      if (!Ember.isEmpty(data)) {
        resolve(data);
      } else {
        reject();
      }
    });
  },
  authenticate(credentials) {
    var self = this;
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
          console.log(data);
          resolve(data);
        });
      }).fail(function(xhr, textStatus, error) {
        Ember.run(function() {
          reject(xhr.responseJSON.error);
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
