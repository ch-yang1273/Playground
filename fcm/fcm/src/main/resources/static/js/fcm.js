import {initializeApp} from "https://www.gstatic.com/firebasejs/9.1.1/firebase-app.js";
import { getMessaging, getToken, onMessage } from 'https://www.gstatic.com/firebasejs/9.1.1/firebase-messaging.js';

const firebaseConfig = {
    apiKey: "AIzaSyCUFgRpA7Wn-yO66k2wLG7uyk7iwtZhbIc",
    authDomain: "fir-test-72df9.firebaseapp.com",
    projectId: "fir-test-72df9",
    storageBucket: "fir-test-72df9.appspot.com",
    messagingSenderId: "36426863828",
    appId: "1:36426863828:web:502dc2060b9fa62e571741",
    measurementId: "G-G269J1W77L"
};

// Initialize Firebase Messaging
const app = initializeApp(firebaseConfig);
const messaging = getMessaging();

// Request user permission for notifications
async function requestPermission() {
    try {
        await Notification.requestPermission();
        if (Notification.permission === 'granted') {
            console.log('Notification permission granted.');
            await getDeviceToken();
        } else {
            console.error('Unable to get permission to notify.');
        }
    } catch (error) {
        console.error('Request for notification permission failed:', error);
    }
}

// Get the device token
async function getDeviceToken() {
    try {
        const deviceToken = await getToken(messaging);
        console.log('Device token:', deviceToken);

        callApi('/api/fcm', {token: deviceToken});
    } catch (error) {
        console.error('Error getting device token:', error);
    }
}

// Listen for messages when the app is in the foreground
onMessage(messaging, (payload) => {
    console.log('Message received. Payload:', payload);
    // You can display the notification using the payload data
    const notificationTitle = payload.data.title;
    const notificationOptions = {
        body: payload.data.body,
        icon: payload.data.icon,
    };
    new Notification(notificationTitle, notificationOptions);
});

async function callApi(url, data) {
    const response = await fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    });
    return response.json();
}

// Add an event listener for the "Give Me Notification" button
document.getElementById('requestNotification').addEventListener('click', () => {
    requestPermission();
});