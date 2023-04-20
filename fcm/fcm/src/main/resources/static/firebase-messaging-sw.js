// firebase-messaging-sw.js

importScripts('https://www.gstatic.com/firebasejs/9.1.1/firebase-app-compat.js');
importScripts('https://www.gstatic.com/firebasejs/9.1.1/firebase-messaging-compat.js');

const firebaseConfig = {
    apiKey: "AIzaSyCUFgRpA7Wn-yO66k2wLG7uyk7iwtZhbIc",
    authDomain: "fir-test-72df9.firebaseapp.com",
    projectId: "fir-test-72df9",
    storageBucket: "fir-test-72df9.appspot.com",
    messagingSenderId: "36426863828",
    appId: "1:36426863828:web:502dc2060b9fa62e571741",
    measurementId: "G-G269J1W77L"
};

firebase.initializeApp(firebaseConfig);

const messaging = firebase.messaging();

messaging.onBackgroundMessage(function(payload) {
    console.log('Received background message ', payload);

    const notificationTitle = 'Background Message Title';
    const notificationOptions = {
        body: 'Background Message body',
        icon: '/firebase-logo.png'
    };

    return self.registration.showNotification(notificationTitle,
        notificationOptions);
});

self.addEventListener('push', function(event) {
    console.log('Received foreground message: ');

    const data = event.data.json();

    const notificationTitle = data.title;
    const notificationOptions = {
        body: data.body,
        icon: '/firebase-logo.png'
    };

    event.waitUntil(
        self.registration.showNotification(notificationTitle,
            notificationOptions)
    );
});
