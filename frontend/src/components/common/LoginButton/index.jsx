import React from 'react';
import styled from 'styled-components';

const GoogleLoginButton = () => {
  const handleLoginClick = () => {
    const googleOAuthURL = `https://accounts.google.com/o/oauth2/v2/auth?response_type=code&client_id=1000456040851-jok3h21lkl27qmm4v2ci41e6tnrr1k1u.apps.googleusercontent.com&scope=email%20profile%20openid&redirect_uri=https://api.agilementor.kr/api/auth/login/code/google`;
    window.location.href = googleOAuthURL;
  };

  return (
    <GoogleButton onClick={handleLoginClick}>
      <GoogleImage src="/image/google_login.png" alt="Google logo" />
    </GoogleButton>
  );
};

export default GoogleLoginButton;

const GoogleButton = styled.button`
  border: none;
  border-radius: 20px;
  background-color: #ffffff;
  cursor: pointer;
  color: #000;
  transition: background-color 0.3s;

  &:hover {
    background-color: #f5f5f5;
  }
`;

const GoogleImage = styled.img`
  width: 140px;
  height: 40px;
`;
