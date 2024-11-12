import React from 'react';
import { styled } from '@mui/material/styles';
import Box from '@mui/material/Box';
import ButtonBase from '@mui/material/ButtonBase';
import Typography from '@mui/material/Typography';

const images = [
  {
    url: '/image/googledrive_icon.png',
    title: 'Google Drive',
    link: 'https://drive.google.com',
  },
  {
    url: '/image/github_icon.png',
    title: 'GitHub',
    link: 'https://github.com',
  },
  {
    url: '/image/notion_icon.png',
    title: 'Notion',
    link: 'https://www.notion.so',
  },
  {
    url: '/image/slack_icon.png',
    title: 'Slack',
    link: 'https://slack.com',
  },
  {
    url: '/image/chatgpt_icon.png',
    title: 'ChatGPT',
    link: 'https://chat.openai.com',
  },
];

const BUTTON_SIZE = '11vh';

const ImageButton = styled(ButtonBase)(({ isFirst, isLast }) => {
  let borderRadius = 0;
  if (isFirst) borderRadius = '12px 0 0 12px';
  else if (isLast) borderRadius = '0 12px 12px 0';

  return {
    position: 'relative',
    width: BUTTON_SIZE,
    height: BUTTON_SIZE,
    margin: 0,
    borderRadius,
    overflow: 'hidden',
    '&:hover .MuiImageBackdrop-root': { opacity: 0.15 },
    '&:hover .MuiImageMarked-root': { opacity: 0 },
    '&:hover .MuiTypography-root': { border: '2px solid currentColor' },
  };
});

const ImageSrc = styled('span')({
  position: 'absolute',
  left: '20%',    
  right: '20%',   
  top: '20%',     
  bottom: '20%',  
  backgroundSize: 'contain', 
  backgroundPosition: 'center',
  backgroundRepeat: 'no-repeat',
});

const ImageBackdrop = styled('span')({
  position: 'absolute',
  left: 0,
  right: 0,
  top: 0,
  bottom: 0,
  backgroundColor: '#000',
  opacity: 0.4,
  transition: 'opacity 0.3s',
});

const ImageMarked = styled('span')({
  height: 2,
  width: 20,
  backgroundColor: '#fff',
  position: 'absolute',
  bottom: 5,
  left: '50%',
  transform: 'translateX(-50%)',
  transition: 'opacity 0.3s',
});

export default function ExternalLinkButtons() {
  return (
    <Box
      sx={{
        display: 'flex',
        flexWrap: 'wrap',
        justifyContent: 'center',
        width: '100%',
      }}
    >
      {images.map((image, index) => (
        <ImageButton
          key={image.title}
          onClick={() => window.open(image.link, '_blank')}
          isFirst={index === 0}
          isLast={index === images.length - 1}
        >
          <ImageSrc style={{ backgroundImage: `url(${image.url})` }} />
          <ImageBackdrop className="MuiImageBackdrop-root" />
          <Box
            sx={{
              position: 'absolute',
              left: 0,
              right: 0,
              top: 0,
              bottom: 0,
              display: 'flex',
              alignItems: 'center',
              justifyContent: 'center',
              color: '#fff',
            }}
          >
            <Typography
              component="span"
              variant="subtitle1"
              color="inherit"
              sx={{
                fontSize: '0.85rem',
              }}
            >
              {image.title}
              <ImageMarked className="MuiImageMarked-root" />
            </Typography>
          </Box>
        </ImageButton>
      ))}
    </Box>
  );
}
